package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class LogitRegressionTest$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements LogitRegressionTest$CoreInterface {
	
	// Declare the variables for the model.
	private double bias;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample39 = false;
	private boolean fixedFlag$sample72 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample39 = false;
	private boolean fixedProbFlag$sample72 = false;
	private boolean[][] guard$sample32bernoulli71$global;
	private boolean[][] guard$sample32put67$global;
	private double[][] indicator;
	private int k;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$indicator;
	private double logProbability$p;
	private double[] logProbability$sample32;
	private double[][] logProbability$sample72;
	private double logProbability$var23;
	private double logProbability$var34;
	private double[][] logProbability$var67;
	private double logProbability$weights;
	private double logProbability$y;
	private int n;
	private double[][] p;
	private boolean setFlag$weights = false;
	private boolean setFlag$y = false;
	private boolean system$gibbsForward = true;
	private double[] weights;
	private double[][] x;
	private boolean[][] y;
	private boolean[][] yMeasured;

	public LogitRegressionTest$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double get$bias() {
		return bias;
	}

	// Setter for bias.
	@Override
	public final void set$bias(double cv$value) {
		// Set flags for all the side effects of bias including if probabilities need to be
		// updated.
		bias = cv$value;
		
		// Unset the fixed probability flag for sample 39 as it depends on bias.
		fixedProbFlag$sample39 = false;
		
		// Unset the fixed probability flag for sample 72 as it depends on bias.
		fixedProbFlag$sample72 = false;
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
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample72 = (fixedFlag$sample32 && fixedProbFlag$sample72);
	}

	// Getter for fixedFlag$sample39.
	@Override
	public final boolean get$fixedFlag$sample39() {
		return fixedFlag$sample39;
	}

	// Setter for fixedFlag$sample39.
	@Override
	public final void set$fixedFlag$sample39(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample39 including if probabilities
		// need to be updated.
		fixedFlag$sample39 = cv$value;
		
		// Should the probability of sample 39 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample39 = (fixedFlag$sample39 && fixedProbFlag$sample39);
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample72 = (fixedFlag$sample39 && fixedProbFlag$sample72);
	}

	// Getter for fixedFlag$sample72.
	@Override
	public final boolean get$fixedFlag$sample72() {
		return fixedFlag$sample72;
	}

	// Setter for fixedFlag$sample72.
	@Override
	public final void set$fixedFlag$sample72(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample72 including if probabilities
		// need to be updated.
		fixedFlag$sample72 = cv$value;
		
		// Should the probability of sample 72 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample72 = (fixedFlag$sample72 && fixedProbFlag$sample72);
	}

	// Getter for k.
	@Override
	public final int get$k() {
		return k;
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

	// Getter for logProbability$bias.
	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	// Getter for logProbability$weights.
	@Override
	public final double get$logProbability$weights() {
		return logProbability$weights;
	}

	// Getter for logProbability$y.
	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	// Getter for n.
	@Override
	public final int get$n() {
		return n;
	}

	// Getter for weights.
	@Override
	public final double[] get$weights() {
		return weights;
	}

	// Setter for weights.
	@Override
	public final void set$weights(double[] cv$value) {
		// Set flags for all the side effects of weights including if probabilities need to
		// be updated.
		// Set weights with flag to mark that it has been set so another array doesn't need
		// to be constructed
		weights = cv$value;
		setFlag$weights = true;
		
		// Unset the fixed probability flag for sample 32 as it depends on weights.
		fixedProbFlag$sample32 = false;
		
		// Unset the fixed probability flag for sample 72 as it depends on weights.
		fixedProbFlag$sample72 = false;
	}

	// Getter for x.
	@Override
	public final double[][] get$x() {
		return x;
	}

	// Setter for x.
	@Override
	public final void set$x(double[][] cv$value) {
		// Set x with flag to mark that it has been set so another array doesn't need to be
		// constructed
		x = cv$value;
	}

	// Getter for y.
	@Override
	public final boolean[][] get$y() {
		return y;
	}

	// Setter for y.
	@Override
	public final void set$y(boolean[][] cv$value) {
		// Set flags for all the side effects of y including if probabilities need to be updated.
		// Set y with flag to mark that it has been set so another array doesn't need to be
		// constructed
		y = cv$value;
		setFlag$y = true;
		
		// Unset the fixed probability flag for sample 72 as it depends on y.
		fixedProbFlag$sample72 = false;
	}

	// Getter for yMeasured.
	@Override
	public final boolean[][] get$yMeasured() {
		return yMeasured;
	}

	// Setter for yMeasured.
	@Override
	public final void set$yMeasured(boolean[][] cv$value) {
		// Set yMeasured with flag to mark that it has been set so another array doesn't need
		// to be constructed
		yMeasured = cv$value;
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
			for(int var27 = 0; var27 < k; var27 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = weights[var27];
					{
						{
							double var21 = 0.0;
							double var22 = 10.0;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var21) / Math.sqrt(var22))) - (0.5 * Math.log(var22))));
							
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
				logProbability$sample32[((var27 - 0) / 1)] = cv$sampleProbability;
				
				// Guard to ensure that indicator is only updated once for this probability.
				boolean cv$guard$indicator = false;
				
				// Guard to ensure that p is only updated once for this probability.
				boolean cv$guard$p = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 32 and consumer double[] 48.
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							for(int i = 0; i < n; i += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$indicator) {
									// Set the guard so the update is only applied once.
									cv$guard$indicator = true;
									
									// Update the variable probability
									logProbability$indicator = (logProbability$indicator + cv$sampleProbability);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 32 and consumer double[] 63.
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 0)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$p) {
											// Set the guard so the update is only applied once.
											cv$guard$p = true;
											
											// Update the variable probability
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 1)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$p) {
											// Set the guard so the update is only applied once.
											cv$guard$p = true;
											
											// Update the variable probability
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 2)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$p) {
											// Set the guard so the update is only applied once.
											cv$guard$p = true;
											
											// Update the variable probability
											logProbability$p = (logProbability$p + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
								if((j$var42 == j$var60)) {
									for(int i = 0; i < n; i += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$p) {
											// Set the guard so the update is only applied once.
											cv$guard$p = true;
											
											// Update the variable probability
											logProbability$p = (logProbability$p + cv$sampleProbability);
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
			logProbability$var23 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$weights = (logProbability$weights + cv$accumulator);
			
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
			for(int var27 = 0; var27 < k; var27 += 1) {
				double cv$sampleValue = logProbability$sample32[((var27 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Guard to ensure that indicator is only updated once for this probability.
				boolean cv$guard$indicator = false;
				
				// Guard to ensure that p is only updated once for this probability.
				boolean cv$guard$p = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				// 
				// Looking for a path between Sample 32 and consumer double[] 48.
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							for(int i = 0; i < n; i += 1) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$indicator) {
									// Set the guard so the update is only applied once.
									cv$guard$indicator = true;
									
									// Update the variable probability
									logProbability$indicator = (logProbability$indicator + cv$sampleValue);
								}
							}
						}
					}
				}
				
				// Looking for a path between Sample 32 and consumer double[] 63.
				{
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 0)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$p) {
											// Set the guard so the update is only applied once.
											cv$guard$p = true;
											
											// Update the variable probability
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 1)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$p) {
											// Set the guard so the update is only applied once.
											cv$guard$p = true;
											
											// Update the variable probability
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							if((j$var42 == 2)) {
								for(int i = 0; i < n; i += 1) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$p) {
											// Set the guard so the update is only applied once.
											cv$guard$p = true;
											
											// Update the variable probability
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
						if((var27 == j$var42)) {
							for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
								if((j$var42 == j$var60)) {
									for(int i = 0; i < n; i += 1) {
										// If the probability of the variable has not already been updated
										if(!cv$guard$p) {
											// Set the guard so the update is only applied once.
											cv$guard$p = true;
											
											// Update the variable probability
											logProbability$p = (logProbability$p + cv$sampleValue);
										}
									}
								}
							}
						}
					}
				}
			}
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var23 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$weights = (logProbability$weights + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample39 using sampled
	// values.
	private final void logProbabilityValue$sample39() {
		// Determine if we need to calculate the values for sample task 39 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample39) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				// The sample value to calculate the probability of generating
				double cv$sampleValue = bias;
				{
					{
						double var32 = 0.0;
						double var33 = 10.0;
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var32) / Math.sqrt(var33))) - (0.5 * Math.log(var33))));
						
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
			logProbability$var34 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$bias = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample39 = fixedFlag$sample39;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$bias;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var34 = cv$rvAccumulator;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample39)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample72 using sampled
	// values.
	private final void logProbabilityValue$sample72() {
		// Determine if we need to calculate the values for sample task 72 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample72) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
					// Accumulator for sample probabilities for a specific instance of the random variable.
					double cv$sampleAccumulator = 0.0;
					
					// An accumulator for log probabilities.
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					
					// An accumulator for the distributed probability space covered.
					double cv$probabilityReached = 0.0;
					{
						// The sample value to calculate the probability of generating
						boolean cv$sampleValue = y[i][j$var60];
						{
							{
								double var66 = (p[((i - 0) / 1)][j$var60] + bias);
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var66));
								
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
					logProbability$var67[((i - 0) / 1)][((j$var60 - 0) / 1)] = cv$sampleAccumulator;
					
					// Store the sample task probability
					logProbability$sample72[((i - 0) / 1)][((j$var60 - 0) / 1)] = cv$sampleProbability;
				}
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample72 = ((fixedFlag$sample72 && fixedFlag$sample32) && fixedFlag$sample39);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			for(int i = 0; i < n; i += 1) {
				for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample72[((i - 0) / 1)][((j$var60 - 0) / 1)];
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var67[((i - 0) / 1)][((j$var60 - 0) / 1)] = cv$rvAccumulator;
				}
			}
			
			// Update the variable probability
			logProbability$y = (logProbability$y + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 32 drawn from Gaussian 23. Inference was performed using Metropolis-Hastings.
	private final void sample32(int var27) {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = weights[var27];
		
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
					double var28 = cv$proposedValue;
					weights[var27] = cv$currentValue;
					
					// Guards to ensure that indicator is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double[] 48.
					{
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1)
									indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
							}
						}
					}
					
					// Guards to ensure that p is only updated when there is a valid path.
					// 
					// Looking for a path between Sample 32 and consumer double[] 63.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample32put67 = guard$sample32put67$global;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 0)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
											// Set the flags to false
											guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 1)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
											// Set the flags to false
											guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 2)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
											// Set the flags to false
											guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if((j$var42 == j$var60)) {
										for(int i = 0; i < n; i += 1)
											// Set the flags to false
											guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 0)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 1)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 2)) {
									for(int i = 0; i < n; i += 1) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
												}
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if((j$var42 == j$var60)) {
										for(int i = 0; i < n; i += 1) {
											if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
												// The body will execute, so should not be executed again
												guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
												{
													p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
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
				double cv$temp$0$var21;
				{
					cv$temp$0$var21 = 0.0;
				}
				double cv$temp$1$var22;
				{
					cv$temp$1$var22 = 10.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var21) / Math.sqrt(cv$temp$1$var22))) - (0.5 * Math.log(cv$temp$1$var22))));
				
				// Processing random variable 67.
				{
					// Looking for a path between Sample 32 and consumer Bernoulli 67.
					{
						// Guard to check that at most one copy of the code is executed for a given random
						// variable instance.
						boolean[][] guard$sample32bernoulli71 = guard$sample32bernoulli71$global;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 0)) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										for(int index$j$10_3 = 0; index$j$10_3 < k; index$j$10_3 += 1) {
											if((j$var60 == index$j$10_3)) {
												for(int i = 0; i < n; i += 1)
													// Set the flags to false
													guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 1)) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										for(int index$j$11_3 = 0; index$j$11_3 < k; index$j$11_3 += 1) {
											if((j$var60 == index$j$11_3)) {
												for(int i = 0; i < n; i += 1)
													// Set the flags to false
													guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								if((j$var42 == 2)) {
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										for(int index$j$12_3 = 0; index$j$12_3 < k; index$j$12_3 += 1) {
											if((j$var60 == index$j$12_3)) {
												for(int i = 0; i < n; i += 1)
													// Set the flags to false
													guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if((j$var42 == j$var60)) {
										for(int index$j$13_3 = 0; index$j$13_3 < k; index$j$13_3 += 1) {
											if((j$var60 == index$j$13_3)) {
												for(int i = 0; i < n; i += 1)
													// Set the flags to false
													guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
											}
										}
									}
								}
							}
						}
						double traceTempVariable$var43$14_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var50$14_4 = Math.exp((traceTempVariable$var43$14_1 * x[i][j$var42]));
									if((j$var42 == 0)) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											double traceTempVariable$var65$14_6 = (indicator[((i - 0) / 1)][j$var60] / ((traceTempVariable$var50$14_4 + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
											for(int index$j$14_7 = 0; index$j$14_7 < k; index$j$14_7 += 1) {
												if((j$var60 == index$j$14_7)) {
													if(!guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
														
														// Processing sample task 72 of consumer random variable null.
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
																			double cv$temp$2$var66;
																			{
																				// Constructing a random variable input for use later.
																				double var66 = (traceTempVariable$var65$14_6 + bias);
																				cv$temp$2$var66 = var66;
																			}
																			
																			// Record the probability of sample task 72 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$14_7], cv$temp$2$var66)));
																			}
																			
																			// Recorded the probability of reaching sample task 72 with the current configuration.
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
						double traceTempVariable$var43$15_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var52$15_4 = Math.exp((traceTempVariable$var43$15_1 * x[i][j$var42]));
									if((j$var42 == 1)) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											double traceTempVariable$var65$15_6 = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + traceTempVariable$var52$15_4) + indicator[((i - 0) / 1)][2]));
											for(int index$j$15_7 = 0; index$j$15_7 < k; index$j$15_7 += 1) {
												if((j$var60 == index$j$15_7)) {
													if(!guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
														
														// Processing sample task 72 of consumer random variable null.
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
																			double cv$temp$3$var66;
																			{
																				// Constructing a random variable input for use later.
																				double var66 = (traceTempVariable$var65$15_6 + bias);
																				cv$temp$3$var66 = var66;
																			}
																			
																			// Record the probability of sample task 72 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$15_7], cv$temp$3$var66)));
																			}
																			
																			// Recorded the probability of reaching sample task 72 with the current configuration.
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
						double traceTempVariable$var43$16_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var55$16_4 = Math.exp((traceTempVariable$var43$16_1 * x[i][j$var42]));
									if((j$var42 == 2)) {
										for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
											double traceTempVariable$var65$16_6 = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + traceTempVariable$var55$16_4));
											for(int index$j$16_7 = 0; index$j$16_7 < k; index$j$16_7 += 1) {
												if((j$var60 == index$j$16_7)) {
													if(!guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
														
														// Processing sample task 72 of consumer random variable null.
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
																			double cv$temp$4$var66;
																			{
																				// Constructing a random variable input for use later.
																				double var66 = (traceTempVariable$var65$16_6 + bias);
																				cv$temp$4$var66 = var66;
																			}
																			
																			// Record the probability of sample task 72 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$16_7], cv$temp$4$var66)));
																			}
																			
																			// Recorded the probability of reaching sample task 72 with the current configuration.
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
						double traceTempVariable$var43$17_1 = cv$currentValue;
						for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
							if((var27 == j$var42)) {
								for(int i = 0; i < n; i += 1) {
									double traceTempVariable$var61$17_4 = Math.exp((traceTempVariable$var43$17_1 * x[i][j$var42]));
									for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
										if((j$var42 == j$var60)) {
											double traceTempVariable$var65$17_6 = (traceTempVariable$var61$17_4 / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
											for(int index$j$17_7 = 0; index$j$17_7 < k; index$j$17_7 += 1) {
												if((j$var60 == index$j$17_7)) {
													if(!guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
														// The body will execute, so should not be executed again
														guard$sample32bernoulli71[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
														
														// Processing sample task 72 of consumer random variable null.
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
																			double cv$temp$5$var66;
																			{
																				// Constructing a random variable input for use later.
																				double var66 = (traceTempVariable$var65$17_6 + bias);
																				cv$temp$5$var66 = var66;
																			}
																			
																			// Record the probability of sample task 72 generating output with current configuration.
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][index$j$17_7], cv$temp$5$var66)));
																			}
																			
																			// Recorded the probability of reaching sample task 72 with the current configuration.
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
			double var28 = cv$originalValue;
			weights[var27] = var28;
			
			// Guards to ensure that indicator is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 48.
			{
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						for(int i = 0; i < n; i += 1)
							indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
					}
				}
			}
			
			// Guards to ensure that p is only updated when there is a valid path.
			// 
			// Looking for a path between Sample 32 and consumer double[] 63.
			{
				// Guard to check that at most one copy of the code is executed for a given random
				// variable instance.
				boolean[][] guard$sample32put67 = guard$sample32put67$global;
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 0)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
									// Set the flags to false
									guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 1)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
									// Set the flags to false
									guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 2)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
									// Set the flags to false
									guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
							if((j$var42 == j$var60)) {
								for(int i = 0; i < n; i += 1)
									// Set the flags to false
									guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 0)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 1)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						if((j$var42 == 2)) {
							for(int i = 0; i < n; i += 1) {
								for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
									if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										}
									}
								}
							}
						}
					}
				}
				for(int j$var42 = 0; j$var42 < k; j$var42 += 1) {
					if((var27 == j$var42)) {
						for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
							if((j$var42 == j$var60)) {
								for(int i = 0; i < n; i += 1) {
									if(!guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)]) {
										// The body will execute, so should not be executed again
										guard$sample32put67[((i - 0) / 1)][((j$var60 - 0) / 1)] = true;
										{
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
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
	// by sample task 39 drawn from Gaussian 34. Inference was performed using Metropolis-Hastings.
	private final void sample39() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// Metropolis-Hastings
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// The original value of the sample
		double cv$originalValue = bias;
		
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
					// Write out the new value of the sample.
					bias = cv$proposedValue;
				}
			}
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var32;
				{
					cv$temp$0$var32 = 0.0;
				}
				double cv$temp$1$var33;
				{
					cv$temp$1$var33 = 10.0;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var32) / Math.sqrt(cv$temp$1$var33))) - (0.5 * Math.log(cv$temp$1$var33))));
				
				// Processing random variable 67.
				{
					{
						for(int i = 0; i < n; i += 1) {
							for(int j$var60 = 0; j$var60 < k; j$var60 += 1) {
								double traceTempVariable$bias$1_3 = cv$currentValue;
								
								// Processing sample task 72 of consumer random variable null.
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
													double cv$temp$2$var66;
													{
														// Constructing a random variable input for use later.
														double var66 = (p[((i - 0) / 1)][j$var60] + traceTempVariable$bias$1_3);
														cv$temp$2$var66 = var66;
													}
													
													// Record the probability of sample task 72 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(y[i][j$var60], cv$temp$2$var66)));
													}
													
													// Recorded the probability of reaching sample task 72 with the current configuration.
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
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio)))
			// If it is not revert the changes.
			// 
			// Set the sample value
			// 
			// Write out the new value of the sample.
			bias = cv$originalValue;
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for guard$sample32put67$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var60 = 0;
			for(int i = 0; i < x.length; i += 1)
				cv$max_j$var60 = Math.max(cv$max_j$var60, ((3 - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((x.length - 0) / 1));
			
			// Allocation of guard$sample32put67$global for single threaded execution
			guard$sample32put67$global = new boolean[cv$max_i][cv$max_j$var60];
		}
		
		// Constructor for guard$sample32bernoulli71$global
		{
			// Calculate the largest index of i that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_i = 0;
			
			// Calculate the largest index of j that is possible and allocate an array to hold
			// the guard for each of these.
			int cv$max_j$var60 = 0;
			for(int i = 0; i < x.length; i += 1)
				cv$max_j$var60 = Math.max(cv$max_j$var60, ((3 - 0) / 1));
			cv$max_i = Math.max(cv$max_i, ((x.length - 0) / 1));
			
			// Allocation of guard$sample32bernoulli71$global for single threaded execution
			guard$sample32bernoulli71$global = new boolean[cv$max_i][cv$max_j$var60];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// If y has not been set already allocate space.
		if(!setFlag$y) {
			// Constructor for y
			{
				y = new boolean[x.length][];
				for(int var16 = 0; var16 < x.length; var16 += 1)
					y[var16] = new boolean[3];
			}
		}
		
		// If weights has not been set already allocate space.
		if(!setFlag$weights) {
			// Constructor for weights
			{
				weights = new double[3];
			}
		}
		
		// Constructor for indicator
		{
			indicator = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				indicator[((i - 0) / 1)] = new double[3];
		}
		
		// Constructor for p
		{
			p = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				p[((i - 0) / 1)] = new double[3];
		}
		
		// Constructor for logProbability$sample32
		{
			logProbability$sample32 = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$var67
		{
			logProbability$var67 = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				logProbability$var67[((i - 0) / 1)] = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		
		// Constructor for logProbability$sample72
		{
			logProbability$sample72 = new double[((((x.length - 1) - 0) / 1) + 1)][];
			for(int i = 0; i < x.length; i += 1)
				logProbability$sample72[((i - 0) / 1)] = new double[((((3 - 1) - 0) / 1) + 1)];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!fixedFlag$sample32)
							weights[var27] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample39)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						boolean[] var64 = y[i];
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample32)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(!fixedFlag$sample32)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
										if(!fixedFlag$sample72)
											var64[j$var60] = DistributionSampling.sampleBernoulli(RNG$2, (p[((i - 0) / 1)][j$var60] + bias));
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
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!fixedFlag$sample32)
							weights[var27] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample39)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample32)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(!fixedFlag$sample32)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
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
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!fixedFlag$sample32)
							weights[var27] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample39)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample32)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(!fixedFlag$sample32)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
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
			for(int var27 = 0; var27 < k; var27 += 1) {
				if(!fixedFlag$sample32)
					sample32(var27);
			}
			if(!fixedFlag$sample39)
				sample39();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample39)
				sample39();
			for(int var27 = (k - ((((k - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
				if(!fixedFlag$sample32)
					sample32(var27);
			}
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		k = 3;
		n = x.length;
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
		logProbability$var23 = 0.0;
		logProbability$weights = 0.0;
		logProbability$p = 0.0;
		logProbability$indicator = 0.0;
		if(!fixedProbFlag$sample32) {
			for(int var27 = 0; var27 < k; var27 += 1)
				logProbability$sample32[((var27 - 0) / 1)] = 0.0;
		}
		logProbability$var34 = 0.0;
		if(!fixedProbFlag$sample39)
			logProbability$bias = 0.0;
		for(int i = 0; i < n; i += 1) {
			for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
				logProbability$var67[((i - 0) / 1)][((j$var60 - 0) / 1)] = 0.0;
		}
		logProbability$y = 0.0;
		if(!fixedProbFlag$sample72) {
			for(int i = 0; i < n; i += 1) {
				for(int j$var60 = 0; j$var60 < k; j$var60 += 1)
					logProbability$sample72[((i - 0) / 1)][((j$var60 - 0) / 1)] = 0.0;
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
		if(fixedFlag$sample39)
			logProbabilityValue$sample39();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample72();
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
		logProbabilityValue$sample39();
		logProbabilityValue$sample72();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, k, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!fixedFlag$sample32)
							weights[var27] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		if(!fixedFlag$sample39)
			bias = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$)) + 0.0);
		
		//  Outer loop for dispatching multiple batches of iterations to execute in parallel
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(!fixedFlag$sample32)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(!fixedFlag$sample32)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
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
		// Deep copy between arrays
		boolean[][] cv$source1 = yMeasured;
		boolean[][] cv$target1 = y;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = cv$source1[cv$index1];
			boolean[] cv$target2 = cv$target1[cv$index1];
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
		parallelFor(RNG$, 0, n, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				
					// Inner loop for running batches of iterations, each batch has its own random number
					// generator.
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var42, int forEnd$j$var42, int threadID$j$var42, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var42 = forStart$j$var42; j$var42 < forEnd$j$var42; j$var42 += 1) {
										if(setFlag$weights)
											indicator[((i - 0) / 1)][j$var42] = Math.exp((weights[j$var42] * x[i][j$var42]));
									}
							}
						);
						
						//  Outer loop for dispatching multiple batches of iterations to execute in parallel
						parallelFor(RNG$1, 0, k, 1,
							(int forStart$j$var60, int forEnd$j$var60, int threadID$j$var60, org.sandwood.random.internal.Rng RNG$2) -> { 
								
									// Inner loop for running batches of iterations, each batch has its own random number
									// generator.
									for(int j$var60 = forStart$j$var60; j$var60 < forEnd$j$var60; j$var60 += 1) {
										if(setFlag$weights)
											p[((i - 0) / 1)][j$var60] = (indicator[((i - 0) / 1)][j$var60] / ((indicator[((i - 0) / 1)][0] + indicator[((i - 0) / 1)][1]) + indicator[((i - 0) / 1)][2]));
									}
							}
						);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\nmodel LogitRegressionTest(double[][] x, boolean[][] yMeasured) {\n    int k = 3;\n\n    int n = x.length;\n    boolean[][] y = new boolean[n][k];\n\n    double[] weights = gaussian(0,10).sample(k);\n    //TODO, change this to a beta distribution.\n    double bias = gaussian(0,10).sample();\n\n    for(int i:[0 .. n)) {\n        double[] indicator = new double[k];\n        for(int j:[0 .. k)) {\n            indicator[j] = exp(weights[j] * x[i][j]);\n        }\n        \n        //Single assignment semantics means a for loop cannot be used here.\n        double sum = indicator[0] + indicator[1] + indicator[2];\n        double[] p = new double[k];\n\n        for(int j:[0 .. k)) {\n            p[j] = indicator[j]/sum;\n            //This really wants to be a Categorical, but for now y will have\n            //to be arrays with just a single value set.\n            y[i][j] = bernoulli(p[j] + bias).sample();\n        }    \n    }\n\n    y.observe(yMeasured);\n}\n";
	}
}