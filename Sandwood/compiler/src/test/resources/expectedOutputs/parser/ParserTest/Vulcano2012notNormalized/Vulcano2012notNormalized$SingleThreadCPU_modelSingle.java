package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Vulcano2012notNormalized$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements Vulcano2012notNormalized$CoreInterface {
	
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
	private double logProbability$ut;
	private double logProbability$var137;
	private double logProbability$var18;
	private double logProbability$var50;
	private double logProbability$var62;
	private double logProbability$var76;
	private double logProbability$var77;
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

	public Vulcano2012notNormalized$SingleThreadCPU(ExecutionTarget target) {
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
		fixedProbFlag$sample141 = (fixedFlag$sample141 && fixedProbFlag$sample141);
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
		
		// Should the probability of sample 141 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample141 = (fixedFlag$sample32 && fixedProbFlag$sample141);
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
		fixedProbFlag$sample64 = (fixedFlag$sample64 && fixedProbFlag$sample64);
		
		// Should the probability of sample 79 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample79 = (fixedFlag$sample64 && fixedProbFlag$sample79);
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
		fixedProbFlag$sample79 = (fixedFlag$sample79 && fixedProbFlag$sample79);
		
		// Should the probability of sample 141 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample141 = (fixedFlag$sample79 && fixedProbFlag$sample141);
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int[] cv$sampleValue = weekly_sales[((t$var89 - 0) / 1)];
					{
						{
							int var136 = arrivals[t$var89];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(cv$sampleValue, weekly_rates[((t$var89 - 0) / 1)], var136));
							
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
				logProbability$sample141[((t$var89 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that Sales is only updated once for this probability.
				boolean cv$guard$Sales = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					for(int j$var148 = 0; j$var148 < noProducts; j$var148 += 1) {
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
			logProbability$var137 = cv$sampleAccumulator;
			
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
			double cv$rvAccumulator = 0.0;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
				double cv$sampleValue = logProbability$sample141[((t$var89 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that Sales is only updated once for this probability.
				boolean cv$guard$Sales = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					for(int j$var148 = 0; j$var148 < noProducts; j$var148 += 1) {
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
			logProbability$var137 = cv$rvAccumulator;
			
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
				
				// Guard to ensure that weekly_ut is only updated once for this probability.
				boolean cv$guard$weekly_ut = false;
				
				// Guard to ensure that weekly_rates is only updated once for this probability.
				boolean cv$guard$weekly_rates = false;
				
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
				
				// Looking for a path between Sample 32 and consumer double[] 109.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
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
				
				// Looking for a path between Sample 32 and consumer double[] 135.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
										if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
											{
												for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
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
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
										if((j$var104 == j$var132)) {
											for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
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
				
				// Guard to ensure that weekly_ut is only updated once for this probability.
				boolean cv$guard$weekly_ut = false;
				
				// Guard to ensure that weekly_rates is only updated once for this probability.
				boolean cv$guard$weekly_rates = false;
				
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
				
				// Looking for a path between Sample 32 and consumer double[] 109.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
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
				
				// Looking for a path between Sample 32 and consumer double[] 135.
				{
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
										if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
											{
												for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
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
					for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
						if((var29 == j$var42)) {
							for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
								if((j$var42 == j$var104)) {
									for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
										if((j$var104 == j$var132)) {
											for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
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
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample64 using sampled
	// values.
	private final void logProbabilityValue$sample64() {
		// Determine if we need to calculate the values for sample task 64 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample64) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var61 = 0; var61 < T; var61 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = lambda[var61];
					{
						{
							double var48 = 10.0;
							double var49 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityGamma(cv$sampleValue, var48, var49));
							
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
			logProbability$var50 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var62 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample64 = fixedFlag$sample64;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var62;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var50 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$lambda = (logProbability$lambda + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample64)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = arrivals[t$var74];
					{
						{
							double var75 = lambda[t$var74];
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$sampleValue, var75));
							
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
			logProbability$var76 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var77 = cv$accumulator;
			
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
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var77;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var76 = cv$rvAccumulator;
			
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
					
					// Guards to ensure that weekly_ut is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double[] 109.
					{
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
											weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
									}
								}
							}
						}
					}
					
					// Guards to ensure that weekly_rates is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double[] 135.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample32put138 = guard$sample32put138$global;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
												{
													for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1)
														// Set the flags to false
														guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
											if((j$var104 == j$var132)) {
												for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
													// Set the flags to false
													guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
												{
													for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
														if(!guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = true;
															{
																// Reduction of array weekly_ut
																// 
																// A generated name to prevent name collisions if the reduction is implemented more
																// than once in inference and probability code. Initialize the variable to the unit
																// value
																double reduceVar$denom$0 = 0.0;
																
																// For each index in the array to be reduced
																for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
																	// Set the left hand term of the reduction function to the return variable value.
																	double k = reduceVar$denom$0;
																	
																	// Set the right hand term to a value from the array weekly_ut
																	double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
																	
																	// Execute the reduction function, saving the result into the return value.
																	// 
																	// Copy the result of the reduction into the variable returned by the reduction.
																	reduceVar$denom$0 = (k + l);
																}
																weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$0);
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
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
											if((j$var104 == j$var132)) {
												for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
													if(!guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = true;
														{
															// Reduction of array weekly_ut
															// 
															// A generated name to prevent name collisions if the reduction is implemented more
															// than once in inference and probability code. Initialize the variable to the unit
															// value
															double reduceVar$denom$1 = 0.0;
															
															// For each index in the array to be reduced
															for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
																// Set the left hand term of the reduction function to the return variable value.
																double k = reduceVar$denom$1;
																
																// Set the right hand term to a value from the array weekly_ut
																double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
																
																// Execute the reduction function, saving the result into the return value.
																// 
																// Copy the result of the reduction into the variable returned by the reduction.
																reduceVar$denom$1 = (k + l);
															}
															weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$1);
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
				
				// Processing random variable 137.
				{
					// Looking for a path between Sample 32 and consumer Multinomial 137.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[] guard$sample32multinomial140 = guard$sample32multinomial140$global;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
												{
													// Set the flags to false
													guard$sample32multinomial140[((t$var89 - 0) / 1)] = false;
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
											if((j$var104 == j$var132)) {
												for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
													// Set the flags to false
													guard$sample32multinomial140[((t$var89 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var43$9_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								double traceTempVariable$var105$9_3 = Math.exp(traceTempVariable$var43$9_1);
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											double traceTempVariable$k$9_6 = (traceTempVariable$var105$9_3 * Avail[t$var89][j$var104]);
											if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
												{
													if(((0 < weekly_ut[((t$var89 - 0) / 1)].length) && (0 < (noProducts + 1)))) {
														// Reduction of array weekly_ut
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$denom$2 = 0.0;
														
														// Reduce for every value except a masked value which will be skipped.
														for(int cv$reduction493Index = 0; cv$reduction493Index < j$var104; cv$reduction493Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$denom$2;
															
															// Set the right hand term to a value from the array weekly_ut
															double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction493Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$denom$2 = (k + l);
														}
														for(int cv$reduction493Index = (j$var104 + 1); cv$reduction493Index < (noProducts + 1); cv$reduction493Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$denom$2;
															
															// Set the right hand term to a value from the array weekly_ut
															double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction493Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$denom$2 = (k + l);
														}
														double cv$reduced118 = reduceVar$denom$2;
														
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$denom$2 = (traceTempVariable$k$9_6 + cv$reduced118);
														double traceTempVariable$denom$9_7 = reduceVar$denom$2;
														if(!guard$sample32multinomial140[((t$var89 - 0) / 1)]) {
															// The body will execute, so should not be executed again
															guard$sample32multinomial140[((t$var89 - 0) / 1)] = true;
															
															// Processing sample task 141 of consumer random variable null.
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
																					cv$temp$2$weekly_rates = weekly_rates[((t$var89 - 0) / 1)];
																				}
																				int cv$temp$3$var136;
																				{
																					// Constructing a random variable input for use later.
																					int var136 = arrivals[t$var89];
																					cv$temp$3$var136 = var136;
																				}
																				
																				// Record the probability of sample task 141 generating output with current configuration.
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$2$weekly_rates, cv$temp$3$var136)));
																				}
																				
																				// Recorded the probability of reaching sample task 141 with the current configuration.
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
						double traceTempVariable$var43$10_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
							if((var29 == j$var42)) {
								double traceTempVariable$var105$10_3 = Math.exp(traceTempVariable$var43$10_1);
								for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
									if((j$var42 == j$var104)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											double traceTempVariable$var133$10_6 = (traceTempVariable$var105$10_3 * Avail[t$var89][j$var104]);
											for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
												if((j$var104 == j$var132)) {
													if(!guard$sample32multinomial140[((t$var89 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample32multinomial140[((t$var89 - 0) / 1)] = true;
														
														// Processing sample task 141 of consumer random variable null.
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
																				cv$temp$4$weekly_rates = weekly_rates[((t$var89 - 0) / 1)];
																			}
																			int cv$temp$5$var136;
																			{
																				// Constructing a random variable input for use later.
																				int var136 = arrivals[t$var89];
																				cv$temp$5$var136 = var136;
																			}
																			
																			// Record the probability of sample task 141 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$4$weekly_rates, cv$temp$5$var136)));
																			}
																			
																			// Recorded the probability of reaching sample task 141 with the current configuration.
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
			
			// Guards to ensure that weekly_ut is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 109.
			{
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
									weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
							}
						}
					}
				}
			}
			
			// Guards to ensure that weekly_rates is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 135.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample32put138 = guard$sample32put138$global;
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
									if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
										{
											for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1)
												// Set the flags to false
												guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = false;
										}
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
									if((j$var104 == j$var132)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
											// Set the flags to false
											guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = false;
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
									if(((0 <= j$var104) && (j$var104 < (noProducts + 1)))) {
										{
											for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
												if(!guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)]) {
													// The body will execute, so should not be executed again
													guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = true;
													{
														// Reduction of array weekly_ut
														// 
														// A generated name to prevent name collisions if the reduction is implemented more
														// than once in inference and probability code. Initialize the variable to the unit
														// value
														double reduceVar$denom$3 = 0.0;
														
														// For each index in the array to be reduced
														for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
															// Set the left hand term of the reduction function to the return variable value.
															double k = reduceVar$denom$3;
															
															// Set the right hand term to a value from the array weekly_ut
															double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
															
															// Execute the reduction function, saving the result into the return value.
															// 
															// Copy the result of the reduction into the variable returned by the reduction.
															reduceVar$denom$3 = (k + l);
														}
														weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$3);
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
				for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
					if((var29 == j$var42)) {
						for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
							if((j$var42 == j$var104)) {
								for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
									if((j$var104 == j$var132)) {
										for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
											if(!guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample32put138[((t$var89 - 0) / 1)][((j$var132 - 0) / 1)] = true;
												{
													// Reduction of array weekly_ut
													// 
													// A generated name to prevent name collisions if the reduction is implemented more
													// than once in inference and probability code. Initialize the variable to the unit
													// value
													double reduceVar$denom$4 = 0.0;
													
													// For each index in the array to be reduced
													for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
														// Set the left hand term of the reduction function to the return variable value.
														double k = reduceVar$denom$4;
														
														// Set the right hand term to a value from the array weekly_ut
														double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
														
														// Execute the reduction function, saving the result into the return value.
														// 
														// Copy the result of the reduction into the variable returned by the reduction.
														reduceVar$denom$4 = (k + l);
													}
													weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$4);
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
	// by sample task 64 drawn from Gamma 50. Inference was performed using a Gamma to
	// Poisson conjugate prior.
	private final void sample64(int var61) {
		// Variable to store the sum of all the samples from consuming random variables.
		double cv$sum = 0.0;
		
		// Variable to record the number of samples from consuming random variables.
		int cv$count = 0;
		{
			// Processing random variable 76.
			{
				// Looking for a path between Sample 64 and consumer Poisson 76.
				{
					for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
						if((var61 == t$var74)) {
							// Processing sample task 79 of consumer random variable null.
							{
								{
									{
										{
											{
												// Add the value of a sample from consuming random variable var76 to the inference
												// state.
												cv$sum = (cv$sum + arrivals[t$var74]);
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
		double var62 = Conjugates.sampleConjugateGammaPoisson(RNG$, 10.0, 10.0, cv$sum, cv$count);
		lambda[var61] = var62;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 79 drawn from Poisson 76. Inference was performed using Metropolis-Hastings.
	private final void sample79(int t$var74) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		int cv$originalValue = arrivals[t$var74];
		
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
					int var77 = cv$proposedValue;
					arrivals[t$var74] = cv$currentValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var75;
				{
					// Constructing a random variable input for use later.
					double var75 = lambda[t$var74];
					cv$temp$0$var75 = var75;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityPoisson(cv$currentValue, cv$temp$0$var75));
				
				// Processing random variable 137.
				{
					// Looking for a path between Sample 79 and consumer Multinomial 137.
					{
						int traceTempVariable$var136$1_1 = cv$currentValue;
						for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
							if((t$var74 == t$var89)) {
								// Processing sample task 141 of consumer random variable null.
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
														cv$temp$1$weekly_rates = weekly_rates[((t$var89 - 0) / 1)];
													}
													int cv$temp$2$var136;
													{
														// Constructing a random variable input for use later.
														int var136 = traceTempVariable$var136$1_1;
														cv$temp$2$var136 = var136;
													}
													
													// Record the probability of sample task 141 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityMultinomial(weekly_sales[((t$var89 - 0) / 1)], cv$temp$1$weekly_rates, cv$temp$2$var136)));
													}
													
													// Recorded the probability of reaching sample task 141 with the current configuration.
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
			int var77 = cv$originalValue;
			arrivals[t$var74] = var77;
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample32put138$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t$var89 = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var132 = 0;
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				cv$max_j$var132 = Math.max(cv$max_j$var132, (((noProducts + 1) - 0) / 1));
			cv$max_t$var89 = Math.max(cv$max_t$var89, ((T - 0) / 1));
			
			// Allocation of guard$sample32put138$global for single threaded execution
			guard$sample32put138$global = new boolean[cv$max_t$var89][cv$max_j$var132];
		}
		
		// Constructor for guard$sample32multinomial140$global
		{
			// Calculate the largest index of t that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_t$var89 = 0;
			cv$max_t$var89 = Math.max(cv$max_t$var89, ((T - 0) / 1));
			
			// Allocation of guard$sample32multinomial140$global for single threaded execution
			guard$sample32multinomial140$global = new boolean[cv$max_t$var89];
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
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				Sales[t$var89] = new int[noProducts];
		}
		
		// Constructor for weekly_rates
		{
			weekly_rates = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				weekly_rates[((t$var89 - 0) / 1)] = new double[(noProducts + 1)];
		}
		
		// Constructor for weekly_ut
		{
			weekly_ut = new double[((((T - 1) - 0) / 1) + 1)][];
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				weekly_ut[((t$var89 - 0) / 1)] = new double[(noProducts + 1)];
		}
		
		// If weekly_sales has not been set already allocate space.
		if(!setFlag$weekly_sales) {
			// Constructor for weekly_sales
			{
				weekly_sales = new int[((((T - 1) - 0) / 1) + 1)][];
				for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
					weekly_sales[((t$var89 - 0) / 1)] = new int[(noProducts + 1)];
			}
		}
		
		// Constructor for logProbability$sample32
		{
			logProbability$sample32 = new double[((((noProducts - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample141
		{
			logProbability$sample141 = new double[((((T - 1) - 0) / 1) + 1)];
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
		for(int var61 = 0; var61 < T; var61 += 1) {
			if(!fixedFlag$sample64)
				lambda[var61] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
			if(!fixedFlag$sample79)
				arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$, lambda[t$var74]);
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(!fixedFlag$sample32)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			weekly_ut[((t$var89 - 0) / 1)][noProducts] = 1.0;
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$5 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k = reduceVar$denom$5;
				
				// Set the right hand term to a value from the array weekly_ut
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample32)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$denom$5 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(!fixedFlag$sample32)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$5);
			}
			if(!fixedFlag$sample141)
				DistributionSampling.sampleMultinomial(RNG$, weekly_rates[((t$var89 - 0) / 1)], arrivals[t$var89], weekly_sales[((t$var89 - 0) / 1)]);
			int[] observed_weekly_sales = Sales[t$var89];
			for(int j$var148 = 0; j$var148 < noProducts; j$var148 += 1) {
				if(!fixedFlag$sample141)
					observed_weekly_sales[j$var148] = weekly_sales[((t$var89 - 0) / 1)][j$var148];
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
		for(int var61 = 0; var61 < T; var61 += 1) {
			if(!fixedFlag$sample64)
				lambda[var61] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
			if(!fixedFlag$sample79)
				arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$, lambda[t$var74]);
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(!fixedFlag$sample32)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			weekly_ut[((t$var89 - 0) / 1)][noProducts] = 1.0;
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$7 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k = reduceVar$denom$7;
				
				// Set the right hand term to a value from the array weekly_ut
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample32)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$denom$7 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(!fixedFlag$sample32)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$7);
			}
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
		for(int var61 = 0; var61 < T; var61 += 1) {
			if(!fixedFlag$sample64)
				lambda[var61] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
			if(!fixedFlag$sample79)
				arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$, lambda[t$var74]);
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(!fixedFlag$sample32)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			weekly_ut[((t$var89 - 0) / 1)][noProducts] = 1.0;
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$6 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k = reduceVar$denom$6;
				
				// Set the right hand term to a value from the array weekly_ut
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample32)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$denom$6 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(!fixedFlag$sample32)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$6);
			}
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
			for(int var61 = 0; var61 < T; var61 += 1) {
				if(!fixedFlag$sample64)
					sample64(var61);
			}
			for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
				if(!fixedFlag$sample79)
					sample79(t$var74);
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			for(int t$var74 = (T - ((((T - 1) - 0) % 1) + 1)); t$var74 >= ((0 - 1) + 1); t$var74 -= 1) {
				if(!fixedFlag$sample79)
					sample79(t$var74);
			}
			for(int var61 = (T - ((((T - 1) - 0) % 1) + 1)); var61 >= ((0 - 1) + 1); var61 -= 1) {
				if(!fixedFlag$sample64)
					sample64(var61);
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
		logProbability$weekly_rates = 0.0;
		logProbability$weekly_ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample32) {
			for(int var29 = 0; var29 < noProducts; var29 += 1)
				logProbability$sample32[((var29 - 0) / 1)] = 0.0;
		}
		logProbability$var50 = 0.0;
		logProbability$lambda = 0.0;
		if(!fixedProbFlag$sample64)
			logProbability$var62 = 0.0;
		logProbability$var76 = 0.0;
		logProbability$arrivals = 0.0;
		if(!fixedProbFlag$sample79)
			logProbability$var77 = 0.0;
		logProbability$var137 = 0.0;
		logProbability$Sales = 0.0;
		logProbability$weekly_sales = 0.0;
		if(!fixedProbFlag$sample141) {
			for(int t$var89 = 0; t$var89 < T; t$var89 += 1)
				logProbability$sample141[((t$var89 - 0) / 1)] = 0.0;
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
		for(int var29 = 0; var29 < noProducts; var29 += 1) {
			if(!fixedFlag$sample32)
				ut[var29] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		}
		for(int j$var42 = 0; j$var42 < noProducts; j$var42 += 1) {
			if(!fixedFlag$sample32)
				exped[j$var42] = Math.exp(ut[j$var42]);
		}
		for(int var61 = 0; var61 < T; var61 += 1) {
			if(!fixedFlag$sample64)
				lambda[var61] = DistributionSampling.sampleGamma(RNG$, 10.0, 10.0);
		}
		for(int t$var74 = 0; t$var74 < T; t$var74 += 1) {
			if(!fixedFlag$sample79)
				arrivals[t$var74] = DistributionSampling.samplePoisson(RNG$, lambda[t$var74]);
		}
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(!fixedFlag$sample32)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			weekly_ut[((t$var89 - 0) / 1)][noProducts] = 1.0;
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$8 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k = reduceVar$denom$8;
				
				// Set the right hand term to a value from the array weekly_ut
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				
				// Execute the reduction function, saving the result into the return value.
				if(!fixedFlag$sample32)
					// Copy the result of the reduction into the variable returned by the reduction.
					reduceVar$denom$8 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(!fixedFlag$sample32)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$8);
			}
		}
		
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
		for(int t$var89 = (T - ((((T - 1) - 0) % 1) + 1)); t$var89 >= ((0 - 1) + 1); t$var89 -= 1) {
			for(int j$var148 = (noProducts - ((((noProducts - 1) - 0) % 1) + 1)); j$var148 >= ((0 - 1) + 1); j$var148 -= 1) {
				int[] observed_weekly_sales;
				observed_weekly_sales = Sales[t$var89];
				weekly_sales[((t$var89 - 0) / 1)][j$var148] = observed_weekly_sales[j$var148];
			}
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
		for(int t$var89 = 0; t$var89 < T; t$var89 += 1) {
			for(int j$var104 = 0; j$var104 < noProducts; j$var104 += 1) {
				if(setFlag$ut)
					weekly_ut[((t$var89 - 0) / 1)][j$var104] = (exped[j$var104] * Avail[t$var89][j$var104]);
			}
			
			// Reduction of array weekly_ut
			// 
			// A generated name to prevent name collisions if the reduction is implemented more
			// than once in inference and probability code. Initialize the variable to the unit
			// value
			double reduceVar$denom$9 = 0.0;
			
			// For each index in the array to be reduced
			for(int cv$reduction118Index = 0; cv$reduction118Index < (noProducts + 1); cv$reduction118Index += 1) {
				// Set the left hand term of the reduction function to the return variable value.
				double k = reduceVar$denom$9;
				
				// Set the right hand term to a value from the array weekly_ut
				double l = weekly_ut[((t$var89 - 0) / 1)][cv$reduction118Index];
				
				// Execute the reduction function, saving the result into the return value.
				// 
				// Copy the result of the reduction into the variable returned by the reduction.
				reduceVar$denom$9 = (k + l);
			}
			for(int j$var132 = 0; j$var132 < (noProducts + 1); j$var132 += 1) {
				if(setFlag$ut)
					weekly_rates[((t$var89 - 0) / 1)][j$var132] = (weekly_ut[((t$var89 - 0) / 1)][j$var132] / reduceVar$denom$9);
			}
			if(setFlag$weekly_sales) {
				int[] observed_weekly_sales = Sales[t$var89];
				for(int j$var148 = 0; j$var148 < noProducts; j$var148 += 1)
					observed_weekly_sales[j$var148] = weekly_sales[((t$var89 - 0) / 1)][j$var148];
			}
		}
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2024, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n/*\n * Model based on the paper Estimating Primary Demand for Substitutable Products from Sales Transaction \n * Data by Gustavo Vulcano, Garrett van Ryzin, and Richard Ratliff.\n * https://business.columbia.edu/sites/default/files-efs/pubfiles/5397/vanRyzin_Estimating.pdf\n */\npackage org.sandwood.compiler.tests.parser;\n\nmodel Vulcano2012notNormalized(int noProducts, int T, int s, int[][] ObsSales, int[][] Avail) {\n    // Avail is the availability matrix, T-by-noProducts\n\n    // draw utilities\n    double[] ut = gaussian(0, 10).sample(noProducts);\n\n    //exponentiate right here (in the non-basic models move to the for loop)\n    double[] exped = new double[noProducts];\n    for(int j : [0..noProducts)) {\n    exped[j] = exp(ut[j]);\n    }\n\n    // priors for the distribution of lambdas (for arrivals). They can be supplied as a vector if RGBU has some estimates, or just use some ad hoc priors\n    double[ ] lambda = gamma(10,10).sample(T);\n\n    // draw arrivals\n    int[] arrivals = new int[T];\n    for (int t : [0..T)){\n    arrivals[t]= poisson(lambda[t]).sample();\n    }\n\n    int[][] Sales = new int[T][];\n\n    for (int t:[0..T)){\n        // for each period t calculate choice probabilities and sales\n\n        double[] weekly_rates = new double[noProducts+1];\n        double[] weekly_ut = new double[noProducts+1];\n\n        for (int j : [0..noProducts)) {\n            weekly_ut[j] = exped[j]*Avail[t][j] ;\n        }\n        // add outside option value (which is always available)\n        weekly_ut[noProducts] = 1.0;\n        double denom = reduce(weekly_ut, 0, (k, l) -> { return k + l; });\n\n        for (int j : [0..noProducts]) {\n            weekly_rates[j] = weekly_ut[j]/denom ;\n        }\n\n        int[] weekly_sales = multinomial(weekly_rates, arrivals[t]).sample();\n\n        //getting rid of the no purchase observation (last one in the vector of weekly_sales)\n        int[] observed_weekly_sales = new int[noProducts];\n        for (int j : [0..noProducts)) {\n            observed_weekly_sales[j] = weekly_sales[j] ;\n        }\n\n        // record sales for period t\n        Sales[t] = observed_weekly_sales;\n\n    }\n    // assert that generated sales match observed sales\n    Sales.observe(ObsSales);\n}";
	}
}