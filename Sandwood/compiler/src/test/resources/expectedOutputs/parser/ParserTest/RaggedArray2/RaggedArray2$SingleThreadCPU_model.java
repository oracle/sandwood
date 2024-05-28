package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class RaggedArray2$SingleThreadCPU extends org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU implements RaggedArray2$CoreInterface {
	
	// Declare the variables for the model.
	private double[][] a;
	private double[][] b;
	private double[] c;
	private double[] cv$var79$stateProbabilityGlobal;
	private double[] cv$var82$stateProbabilityGlobal;
	private double[] distribution$sample84;
	private boolean fixedFlag$sample104 = false;
	private boolean fixedFlag$sample84 = false;
	private boolean fixedFlag$sample87 = false;
	private boolean fixedProbFlag$sample104 = false;
	private boolean fixedProbFlag$sample84 = false;
	private boolean fixedProbFlag$sample87 = false;
	private int i;
	private int length$obs_measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$i;
	private double logProbability$obs;
	private double logProbability$p;
	private double logProbability$var78;
	private double logProbability$var81;
	private double logProbability$var85;
	private double logProbability$var98;
	private double logProbability$y;
	private boolean[] obs;
	private boolean[] obs_measured;
	private double p;
	private boolean setFlag$obs = false;
	private boolean system$gibbsForward = true;
	private int y;

	public RaggedArray2$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for a.
	@Override
	public final double[][] get$a() {
		return a;
	}

	// Getter for b.
	@Override
	public final double[][] get$b() {
		return b;
	}

	// Getter for c.
	@Override
	public final double[] get$c() {
		return c;
	}

	// Getter for fixedFlag$sample104.
	@Override
	public final boolean get$fixedFlag$sample104() {
		return fixedFlag$sample104;
	}

	// Setter for fixedFlag$sample104.
	@Override
	public final void set$fixedFlag$sample104(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample104 including if probabilities
		// need to be updated.
		fixedFlag$sample104 = cv$value;
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample104 = (fixedFlag$sample104 && fixedProbFlag$sample104);
	}

	// Getter for fixedFlag$sample84.
	@Override
	public final boolean get$fixedFlag$sample84() {
		return fixedFlag$sample84;
	}

	// Setter for fixedFlag$sample84.
	@Override
	public final void set$fixedFlag$sample84(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample84 including if probabilities
		// need to be updated.
		fixedFlag$sample84 = cv$value;
		
		// Should the probability of sample 84 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample84 = (fixedFlag$sample84 && fixedProbFlag$sample84);
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample87 = (fixedFlag$sample84 && fixedProbFlag$sample87);
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample104 = (fixedFlag$sample84 && fixedProbFlag$sample104);
	}

	// Getter for fixedFlag$sample87.
	@Override
	public final boolean get$fixedFlag$sample87() {
		return fixedFlag$sample87;
	}

	// Setter for fixedFlag$sample87.
	@Override
	public final void set$fixedFlag$sample87(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample87 including if probabilities
		// need to be updated.
		fixedFlag$sample87 = cv$value;
		
		// Should the probability of sample 87 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedProbFlag$sample87);
		
		// Should the probability of sample 104 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample104 = (fixedFlag$sample87 && fixedProbFlag$sample104);
	}

	// Getter for i.
	@Override
	public final int get$i() {
		return i;
	}

	// Setter for i.
	@Override
	public final void set$i(int cv$value) {
		// Set flags for all the side effects of i including if probabilities need to be updated.
		i = cv$value;
		
		// Unset the fixed probability flag for sample 87 as it depends on i.
		fixedProbFlag$sample87 = false;
		
		// Unset the fixed probability flag for sample 104 as it depends on i.
		fixedProbFlag$sample104 = false;
	}

	// Getter for length$obs_measured.
	@Override
	public final int get$length$obs_measured() {
		return length$obs_measured;
	}

	// Setter for length$obs_measured.
	@Override
	public final void set$length$obs_measured(int cv$value) {
		length$obs_measured = cv$value;
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

	// Getter for logProbability$i.
	@Override
	public final double get$logProbability$i() {
		return logProbability$i;
	}

	// Getter for logProbability$obs.
	@Override
	public final double get$logProbability$obs() {
		return logProbability$obs;
	}

	// Getter for logProbability$p.
	@Override
	public final double get$logProbability$p() {
		return logProbability$p;
	}

	// Getter for logProbability$y.
	@Override
	public final double get$logProbability$y() {
		return logProbability$y;
	}

	// Getter for obs.
	@Override
	public final boolean[] get$obs() {
		return obs;
	}

	// Setter for obs.
	@Override
	public final void set$obs(boolean[] cv$value) {
		// Set flags for all the side effects of obs including if probabilities need to be
		// updated.
		// Set obs with flag to mark that it has been set so another array doesn't need to
		// be constructed
		obs = cv$value;
		setFlag$obs = true;
		
		// Unset the fixed probability flag for sample 104 as it depends on obs.
		fixedProbFlag$sample104 = false;
	}

	// Getter for obs_measured.
	@Override
	public final boolean[] get$obs_measured() {
		return obs_measured;
	}

	// Setter for obs_measured.
	@Override
	public final void set$obs_measured(boolean[] cv$value) {
		// Set obs_measured with flag to mark that it has been set so another array doesn't
		// need to be constructed
		obs_measured = cv$value;
	}

	// Getter for p.
	@Override
	public final double get$p() {
		return p;
	}

	// Getter for y.
	@Override
	public final int get$y() {
		return y;
	}

	// Setter for y.
	@Override
	public final void set$y(int cv$value) {
		// Set flags for all the side effects of y including if probabilities need to be updated.
		y = cv$value;
		
		// Unset the fixed probability flag for sample 84 as it depends on y.
		fixedProbFlag$sample84 = false;
		
		// Unset the fixed probability flag for sample 87 as it depends on y.
		fixedProbFlag$sample87 = false;
		
		// Unset the fixed probability flag for sample 104 as it depends on y.
		fixedProbFlag$sample104 = false;
	}

	// Calculate the probability of the samples represented by sample104 using probability
	// distributions.
	private final void logProbabilityDistribution$sample104() {
		// Determine if we need to calculate the values for sample task 104 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample104) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 104 including any distribution
				// values.
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = obs[var97];
					
					// Enumerating the possible arguments for Bernoulli 85.
					if(fixedFlag$sample84) {
						double traceTempVariable$p$8_1 = 0.2;
						if((0 == y)) {
							if((0 == i)) {
								{
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$8_1));
									
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
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 78.
							for(int index$sample84$4 = 0; index$sample84$4 < 2; index$sample84$4 += 1) {
								int distributionTempVariable$y$6 = index$sample84$4;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample84Value5 = (1.0 * distribution$sample84[index$sample84$4]);
								double traceTempVariable$p$9_1 = 0.2;
								if((0 == distributionTempVariable$y$6)) {
									if((0 == i)) {
										{
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample84Value5) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$9_1));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value5);
										}
									}
								}
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 85.
					if(fixedFlag$sample84) {
						double traceTempVariable$p$16_1 = 0.8;
						if((0 == y)) {
							if((1 == i)) {
								{
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$16_1));
									
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
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 78.
							for(int index$sample84$12 = 0; index$sample84$12 < 2; index$sample84$12 += 1) {
								int distributionTempVariable$y$14 = index$sample84$12;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample84Value13 = (1.0 * distribution$sample84[index$sample84$12]);
								double traceTempVariable$p$17_1 = 0.8;
								if((0 == distributionTempVariable$y$14)) {
									if((1 == i)) {
										{
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample84Value13) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$17_1));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value13);
										}
									}
								}
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 85.
					if(fixedFlag$sample84) {
						double traceTempVariable$p$24_1 = 0.4;
						if((1 == y)) {
							if((0 == i)) {
								{
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$24_1));
									
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
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 78.
							for(int index$sample84$20 = 0; index$sample84$20 < 2; index$sample84$20 += 1) {
								int distributionTempVariable$y$22 = index$sample84$20;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample84Value21 = (1.0 * distribution$sample84[index$sample84$20]);
								double traceTempVariable$p$25_1 = 0.4;
								if((1 == distributionTempVariable$y$22)) {
									if((0 == i)) {
										{
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample84Value21) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$25_1));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value21);
										}
									}
								}
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 85.
					if(fixedFlag$sample84) {
						double traceTempVariable$p$32_1 = 0.2;
						if((1 == y)) {
							if((1 == i)) {
								{
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$32_1));
									
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
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 78.
							for(int index$sample84$28 = 0; index$sample84$28 < 2; index$sample84$28 += 1) {
								int distributionTempVariable$y$30 = index$sample84$28;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample84Value29 = (1.0 * distribution$sample84[index$sample84$28]);
								double traceTempVariable$p$33_1 = 0.2;
								if((1 == distributionTempVariable$y$30)) {
									if((1 == i)) {
										{
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample84Value29) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$33_1));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value29);
										}
									}
								}
							}
						}
					}
					
					// Enumerating the possible arguments for Bernoulli 85.
					if(fixedFlag$sample84) {
						double traceTempVariable$p$40_1 = 0.6;
						if((1 == y)) {
							if((2 == i)) {
								{
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$40_1));
									
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
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 78.
							for(int index$sample84$36 = 0; index$sample84$36 < 2; index$sample84$36 += 1) {
								int distributionTempVariable$y$38 = index$sample84$36;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample84Value37 = (1.0 * distribution$sample84[index$sample84$36]);
								double traceTempVariable$p$41_1 = 0.6;
								if((1 == distributionTempVariable$y$38)) {
									if((2 == i)) {
										{
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample84Value37) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, traceTempVariable$p$41_1));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value37);
										}
									}
								}
							}
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
			logProbability$var85 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var98 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$obs = (logProbability$obs + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample104 = ((fixedFlag$sample104 && fixedFlag$sample84) && fixedFlag$sample87);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var98;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var85 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$obs = (logProbability$obs + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample84 using probability
	// distributions.
	private final void logProbabilityDistribution$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample84) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample84) {
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
					int cv$sampleValue = y;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < c.length))?Math.log(c[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
				logProbability$var78 = cv$sampleAccumulator;
				
				// Store the sample task probability
				logProbability$y = cv$sampleProbability;
				
				// Guard to ensure that p is only updated once for this probability.
				boolean cv$guard$p = false;
				
				// Add probability to constructed variables from the combined probability
				{
					// Make sure all the inputs have been fixed so the variable is not a distribution.
					if((fixedFlag$sample84 && fixedFlag$sample87)) {
						// If the probability of the variable has not already been updated
						if(!cv$guard$p) {
							// Set the guard so the update is only applied once.
							cv$guard$p = true;
							
							// Update the variable probability
							logProbability$p = (logProbability$p + cv$accumulator);
						}
					}
				}
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample84)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample84 = fixedFlag$sample84;
			}
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$y;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var78 = cv$rvAccumulator;
			
			// Guard to ensure that p is only updated once for this probability.
			boolean cv$guard$p = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if((fixedFlag$sample84 && fixedFlag$sample87)) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$p) {
						// Set the guard so the update is only applied once.
						cv$guard$p = true;
						
						// Update the variable probability
						logProbability$p = (logProbability$p + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample87 using probability
	// distributions.
	private final void logProbabilityDistribution$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample87) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			
			// Look for paths between the variable and the sample task 87 including any distribution
			// values.
			{
				// The sample value to calculate the probability of generating
				int cv$sampleValue = i;
				
				// Enumerating the possible arguments for Categorical 81.
				if(fixedFlag$sample84) {
					if((0 == y)) {
						if((0 == y)) {
							{
								double[] var80 = a[y];
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				} else {
					if(true) {
						// Enumerating the possible outputs of Categorical 78.
						for(int index$sample84$3 = 0; index$sample84$3 < 2; index$sample84$3 += 1) {
							int distributionTempVariable$y$5 = index$sample84$3;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample84Value4 = (1.0 * distribution$sample84[index$sample84$3]);
							if((0 == distributionTempVariable$y$5)) {
								if((0 == distributionTempVariable$y$5)) {
									{
										double[] var80 = a[distributionTempVariable$y$5];
										
										// Store the value of the function call, so the function call is only made once.
										double cv$weightedProbability = (Math.log(cv$probabilitySample84Value4) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
										
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
										cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value4);
									}
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Categorical 81.
				if(fixedFlag$sample84) {
					if((1 == y)) {
						if((1 == y)) {
							if((1 == y)) {
								{
									double[] var80 = a[y];
									
									// Store the value of the function call, so the function call is only made once.
									double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									
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
					}
				} else {
					if(true) {
						// Enumerating the possible outputs of Categorical 78.
						for(int index$sample84$12 = 0; index$sample84$12 < 2; index$sample84$12 += 1) {
							int distributionTempVariable$y$14 = index$sample84$12;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample84Value13 = (1.0 * distribution$sample84[index$sample84$12]);
							if((1 == distributionTempVariable$y$14)) {
								if((1 == distributionTempVariable$y$14)) {
									if((1 == distributionTempVariable$y$14)) {
										{
											double[] var80 = a[distributionTempVariable$y$14];
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(cv$probabilitySample84Value13) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
											
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
											cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample84Value13);
										}
									}
								}
							}
						}
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
			logProbability$var81 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$i = cv$sampleProbability;
			
			// Guard to ensure that p is only updated once for this probability.
			boolean cv$guard$p = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if((fixedFlag$sample84 && fixedFlag$sample87)) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$p) {
						// Set the guard so the update is only applied once.
						cv$guard$p = true;
						
						// Update the variable probability
						logProbability$p = (logProbability$p + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedFlag$sample84);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$i;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var81 = cv$rvAccumulator;
			
			// Guard to ensure that p is only updated once for this probability.
			boolean cv$guard$p = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// Make sure all the inputs have been fixed so the variable is not a distribution.
				if((fixedFlag$sample84 && fixedFlag$sample87)) {
					// If the probability of the variable has not already been updated
					if(!cv$guard$p) {
						// Set the guard so the update is only applied once.
						cv$guard$p = true;
						
						// Update the variable probability
						logProbability$p = (logProbability$p + cv$accumulator);
					}
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample104 using sampled
	// values.
	private final void logProbabilityValue$sample104() {
		// Determine if we need to calculate the values for sample task 104 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample104) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					// The sample value to calculate the probability of generating
					boolean cv$sampleValue = obs[var97];
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, p));
							
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
			logProbability$var85 = cv$sampleAccumulator;
			
			// Store the random variable instance probability
			logProbability$var98 = cv$sampleAccumulator;
			
			// Update the variable probability
			logProbability$obs = (logProbability$obs + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample104 = ((fixedFlag$sample104 && fixedFlag$sample84) && fixedFlag$sample87);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var98;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var85 = cv$rvAccumulator;
			
			// Update the variable probability
			logProbability$obs = (logProbability$obs + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample84 using sampled
	// values.
	private final void logProbabilityValue$sample84() {
		// Determine if we need to calculate the values for sample task 84 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample84) {
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
				int cv$sampleValue = y;
				{
					{
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < c.length))?Math.log(c[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var78 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$y = cv$sampleProbability;
			
			// Guard to ensure that p is only updated once for this probability.
			boolean cv$guard$p = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$p) {
					// Set the guard so the update is only applied once.
					cv$guard$p = true;
					
					// Update the variable probability
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample84 = fixedFlag$sample84;
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$y;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var78 = cv$rvAccumulator;
			
			// Guard to ensure that p is only updated once for this probability.
			boolean cv$guard$p = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$p) {
					// Set the guard so the update is only applied once.
					cv$guard$p = true;
					
					// Update the variable probability
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample84)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample87 using sampled
	// values.
	private final void logProbabilityValue$sample87() {
		// Determine if we need to calculate the values for sample task 87 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample87) {
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
				int cv$sampleValue = i;
				{
					{
						double[] var80 = a[y];
						
						// Store the value of the function call, so the function call is only made once.
						double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < var80.length))?Math.log(var80[cv$sampleValue]):Double.NEGATIVE_INFINITY));
						
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
			logProbability$var81 = cv$sampleAccumulator;
			
			// Store the sample task probability
			logProbability$i = cv$sampleProbability;
			
			// Guard to ensure that p is only updated once for this probability.
			boolean cv$guard$p = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$p) {
					// Set the guard so the update is only applied once.
					cv$guard$p = true;
					
					// Update the variable probability
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample87 = (fixedFlag$sample87 && fixedFlag$sample84);
		}
		// Using cached values.
		else {
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$i;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var81 = cv$rvAccumulator;
			
			// Guard to ensure that p is only updated once for this probability.
			boolean cv$guard$p = false;
			
			// Add probability to constructed variables from the combined probability
			{
				// If the probability of the variable has not already been updated
				if(!cv$guard$p) {
					// Set the guard so the update is only applied once.
					cv$guard$p = true;
					
					// Update the variable probability
					logProbability$p = (logProbability$p + cv$accumulator);
				}
			}
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample87)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 84 drawn from Categorical 78. Inference was performed using variable
	// marginalization.
	private final void sample84() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		{
			// variable marginalization
			cv$noStates = Math.max(cv$noStates, 2);
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var79$stateProbabilityGlobal;
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
			
			// Value of the variable at this index
			cv$currentValue = cv$valuePos;
			{
				// Record the reached probability density.
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double[] cv$temp$0$c;
				{
					cv$temp$0$c = c;
				}
				
				// An accumulator to allow the value for each distribution to be constructed before
				// it is added to the index probabilities.
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$c.length))?Math.log(cv$temp$0$c[cv$currentValue]):Double.NEGATIVE_INFINITY));
				
				// Processing random variable 81.
				{
					{
						int traceTempVariable$y$1_1 = cv$currentValue;
						
						// Processing sample task 87 of consumer random variable null.
						{
							// Set an accumulator to sum the probabilities for each possible configuration of
							// inputs.
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							
							// Set an accumulator to record the consumer distributions not seen. Initially set
							// to 1 as seen values will be deducted from this value.
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							{
								// Enumerating the possible arguments for the variable Categorical 81 which is consuming
								// the output of Sample task 84.
								if((0 == traceTempVariable$y$1_1)) {
									if((0 == traceTempVariable$y$1_1)) {
										{
											{
												double[] cv$temp$1$var80;
												{
													// Constructing a random variable input for use later.
													double[] var80 = a[traceTempVariable$y$1_1];
													cv$temp$1$var80 = var80;
												}
												
												// Record the probability of sample task 87 generating output with current configuration.
												if(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[i]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[i]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
												else {
													// If the second value is -infinity.
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[i]):Double.NEGATIVE_INFINITY));
													else
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[i]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[i]):Double.NEGATIVE_INFINITY)));
												}
												
												// Recorded the probability of reaching sample task 87 with the current configuration.
												cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
											}
										}
									}
								}
								
								// Enumerating the possible arguments for the variable Categorical 81 which is consuming
								// the output of Sample task 84.
								if((1 == traceTempVariable$y$1_1)) {
									if((1 == traceTempVariable$y$1_1)) {
										if((1 == traceTempVariable$y$1_1)) {
											{
												{
													double[] cv$temp$2$var80;
													{
														// Constructing a random variable input for use later.
														double[] var80 = a[traceTempVariable$y$1_1];
														cv$temp$2$var80 = var80;
													}
													
													// Record the probability of sample task 87 generating output with current configuration.
													if(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[i]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[i]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[i]):Double.NEGATIVE_INFINITY));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[i]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= i) && (i < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[i]):Double.NEGATIVE_INFINITY)));
													}
													
													// Recorded the probability of reaching sample task 87 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
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
				
				// Processing random variable 85.
				{
					{
						int traceTempVariable$y$10_1 = cv$currentValue;
						
						// Processing sample task 104 of consumer random variable null.
						{
							for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
								// Set an accumulator to sum the probabilities for each possible configuration of
								// inputs.
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								
								// Set an accumulator to record the consumer distributions not seen. Initially set
								// to 1 as seen values will be deducted from this value.
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								{
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 84.
									double traceTempVariable$p$13_1 = 0.2;
									if((0 == traceTempVariable$y$10_1)) {
										if((0 == i)) {
											{
												{
													double cv$temp$3$p;
													{
														cv$temp$3$p = traceTempVariable$p$13_1;
													}
													
													// Record the probability of sample task 104 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$3$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$3$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$3$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$3$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$3$p)));
													}
													
													// Recorded the probability of reaching sample task 104 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 84.
									double traceTempVariable$p$15_1 = 0.8;
									if((0 == traceTempVariable$y$10_1)) {
										if((1 == i)) {
											{
												{
													double cv$temp$4$p;
													{
														cv$temp$4$p = traceTempVariable$p$15_1;
													}
													
													// Record the probability of sample task 104 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p)));
													}
													
													// Recorded the probability of reaching sample task 104 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 84.
									double traceTempVariable$p$17_1 = 0.4;
									if((1 == traceTempVariable$y$10_1)) {
										if((0 == i)) {
											{
												{
													double cv$temp$5$p;
													{
														cv$temp$5$p = traceTempVariable$p$17_1;
													}
													
													// Record the probability of sample task 104 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p)));
													}
													
													// Recorded the probability of reaching sample task 104 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 84.
									double traceTempVariable$p$19_1 = 0.2;
									if((1 == traceTempVariable$y$10_1)) {
										if((1 == i)) {
											{
												{
													double cv$temp$6$p;
													{
														cv$temp$6$p = traceTempVariable$p$19_1;
													}
													
													// Record the probability of sample task 104 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p)));
													}
													
													// Recorded the probability of reaching sample task 104 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									
									// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
									// the output of Sample task 84.
									double traceTempVariable$p$21_1 = 0.6;
									if((1 == traceTempVariable$y$10_1)) {
										if((2 == i)) {
											{
												{
													double cv$temp$7$p;
													{
														cv$temp$7$p = traceTempVariable$p$21_1;
													}
													
													// Record the probability of sample task 104 generating output with current configuration.
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														// If the second value is -infinity.
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p)));
													}
													
													// Recorded the probability of reaching sample task 104 with the current configuration.
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
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
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// Set the calculated probabilities to be the distribution values, and normalize
		// 
		// Local copy of the probability array
		double[] cv$localProbability = distribution$sample84;
		
		// The sum of all the probabilities in log space
		double cv$logSum = 0.0;
		
		// Sum all the values
		{
			// Initialise the max to the first element.
			double cv$lseMax = cv$stateProbabilityLocal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialise the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 87 drawn from Categorical 81. Inference was performed using variable
	// marginalization.
	private final void sample87() {
		// Calculate the number of states to evaluate.
		int cv$noStates = 0;
		
		// Exploring all the possible state counts for random variable 81.
		// 
		// Enumerating the possible arguments for Categorical 81.
		if(fixedFlag$sample84) {
			if((0 == y)) {
				if((0 == y))
					// variable marginalization
					cv$noStates = Math.max(cv$noStates, a[y].length);
			}
		} else {
			if(true) {
				// Enumerating the possible outputs of Categorical 78.
				for(int index$sample84$2 = 0; index$sample84$2 < 2; index$sample84$2 += 1) {
					int distributionTempVariable$y$4 = index$sample84$2;
					
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample84Value3 = (1.0 * distribution$sample84[index$sample84$2]);
					if((0 == distributionTempVariable$y$4)) {
						if((0 == distributionTempVariable$y$4))
							// variable marginalization
							cv$noStates = Math.max(cv$noStates, a[distributionTempVariable$y$4].length);
					}
				}
			}
		}
		
		// Enumerating the possible arguments for Categorical 81.
		if(fixedFlag$sample84) {
			if((1 == y)) {
				if((1 == y)) {
					if((1 == y))
						// variable marginalization
						cv$noStates = Math.max(cv$noStates, a[y].length);
				}
			}
		} else {
			if(true) {
				// Enumerating the possible outputs of Categorical 78.
				for(int index$sample84$11 = 0; index$sample84$11 < 2; index$sample84$11 += 1) {
					int distributionTempVariable$y$13 = index$sample84$11;
					
					// Update the probability of sampling this value from the distribution value.
					double cv$probabilitySample84Value12 = (1.0 * distribution$sample84[index$sample84$11]);
					if((1 == distributionTempVariable$y$13)) {
						if((1 == distributionTempVariable$y$13)) {
							if((1 == distributionTempVariable$y$13))
								// variable marginalization
								cv$noStates = Math.max(cv$noStates, a[distributionTempVariable$y$13].length);
						}
					}
				}
			}
		}
		
		// Get a local reference to the scratch space.
		double[] cv$stateProbabilityLocal = cv$var82$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			// Exploring all the possible distribution values for random variable 81 creating
			// sample task 87.
			// Initialize the summed probabilities to 0.
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			
			// Initialize a counter to track the reached distributions.
			double cv$reachedDistributionSourceRV = 0.0;
			
			// Initialize a log space accumulator to take the product of all the distribution
			// probabilities.
			double cv$accumulatedDistributionProbabilities = 0.0;
			
			// The value currently being tested
			int cv$currentValue;
			
			// Value of the variable at this index
			cv$currentValue = cv$valuePos;
			
			// Write out the new value of the sample.
			i = cv$currentValue;
			
			// Guards to ensure that p is only updated when there is a valid path.
			{
				{
					// Write out the new sample value.
					p = b[y][cv$currentValue];
				}
			}
			
			// Enumerating the possible arguments for Categorical 81.
			if(fixedFlag$sample84) {
				if((0 == y)) {
					if((0 == y)) {
						// Record the reached probability density.
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double[] cv$temp$0$var80;
						{
							// Constructing a random variable input for use later.
							double[] var80 = a[y];
							cv$temp$0$var80 = var80;
						}
						
						// An accumulator to allow the value for each distribution to be constructed before
						// it is added to the index probabilities.
						double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$0$var80.length))?Math.log(cv$temp$0$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
						
						// Processing random variable 85.
						{
							{
								int traceTempVariable$i$42_1 = cv$currentValue;
								
								// Processing sample task 104 of consumer random variable null.
								{
									for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
											// the output of Sample task 87.
											double traceTempVariable$p$51_1 = 0.2;
											if((0 == y)) {
												if((0 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$4$p;
															{
																cv$temp$4$p = traceTempVariable$p$51_1;
															}
															
															// Record the probability of sample task 104 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$4$p)));
															}
															
															// Recorded the probability of reaching sample task 104 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											
											// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
											// the output of Sample task 87.
											double traceTempVariable$p$53_1 = 0.8;
											if((0 == y)) {
												if((1 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$5$p;
															{
																cv$temp$5$p = traceTempVariable$p$53_1;
															}
															
															// Record the probability of sample task 104 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$5$p)));
															}
															
															// Recorded the probability of reaching sample task 104 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											
											// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
											// the output of Sample task 87.
											double traceTempVariable$p$55_1 = 0.4;
											if((1 == y)) {
												if((0 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$6$p;
															{
																cv$temp$6$p = traceTempVariable$p$55_1;
															}
															
															// Record the probability of sample task 104 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$6$p)));
															}
															
															// Recorded the probability of reaching sample task 104 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											
											// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
											// the output of Sample task 87.
											double traceTempVariable$p$57_1 = 0.2;
											if((1 == y)) {
												if((1 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$7$p;
															{
																cv$temp$7$p = traceTempVariable$p$57_1;
															}
															
															// Record the probability of sample task 104 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$7$p)));
															}
															
															// Recorded the probability of reaching sample task 104 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
													}
												}
											}
											
											// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
											// the output of Sample task 87.
											double traceTempVariable$p$59_1 = 0.6;
											if((1 == y)) {
												if((2 == traceTempVariable$i$42_1)) {
													{
														{
															double cv$temp$8$p;
															{
																cv$temp$8$p = traceTempVariable$p$59_1;
															}
															
															// Record the probability of sample task 104 generating output with current configuration.
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$8$p)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$8$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																// If the second value is -infinity.
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$8$p));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$8$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$8$p)));
															}
															
															// Recorded the probability of reaching sample task 104 with the current configuration.
															cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
														}
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
				}
			} else {
				if(true) {
					// Enumerating the possible outputs of Categorical 78.
					for(int index$sample84$23 = 0; index$sample84$23 < 2; index$sample84$23 += 1) {
						int distributionTempVariable$y$25 = index$sample84$23;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample84Value24 = (1.0 * distribution$sample84[index$sample84$23]);
						if((0 == distributionTempVariable$y$25)) {
							if((0 == distributionTempVariable$y$25)) {
								// Record the reached probability density.
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample84Value24);
								double[] cv$temp$1$var80;
								{
									// Constructing a random variable input for use later.
									double[] var80 = a[distributionTempVariable$y$25];
									cv$temp$1$var80 = var80;
								}
								
								// An accumulator to allow the value for each distribution to be constructed before
								// it is added to the index probabilities.
								double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample84Value24) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$1$var80.length))?Math.log(cv$temp$1$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
								
								// Processing random variable 85.
								{
									{
										int traceTempVariable$i$43_1 = cv$currentValue;
										
										// Processing sample task 104 of consumer random variable null.
										{
											for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
												// Set an accumulator to sum the probabilities for each possible configuration of
												// inputs.
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												
												// Set an accumulator to record the consumer distributions not seen. Initially set
												// to 1 as seen values will be deducted from this value.
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
													// the output of Sample task 87.
													if(!true) {
														// Enumerating the possible outputs of Categorical 78.
														for(int index$sample84$61 = 0; index$sample84$61 < 2; index$sample84$61 += 1) {
															int distributionTempVariable$y$63 = index$sample84$61;
															
															// Update the probability of sampling this value from the distribution value.
															double cv$probabilitySample84Value62 = (1.0 * distribution$sample84[index$sample84$61]);
															double traceTempVariable$p$66_1 = 0.2;
															if((0 == distributionTempVariable$y$63)) {
																if((0 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$10$p;
																			{
																				cv$temp$10$p = traceTempVariable$p$66_1;
																			}
																			
																			// Record the probability of sample task 104 generating output with current configuration.
																			if(((Math.log(cv$probabilitySample84Value62) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$10$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value62) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$10$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value62) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$10$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value62) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$10$p)))) + 1)) + (Math.log(cv$probabilitySample84Value62) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$10$p)));
																			}
																			
																			// Recorded the probability of reaching sample task 104 with the current configuration.
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value62);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$65_1 = 0.2;
													if((0 == distributionTempVariable$y$25)) {
														if((0 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$9$p;
																	{
																		cv$temp$9$p = traceTempVariable$p$65_1;
																	}
																	
																	// Record the probability of sample task 104 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$9$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$9$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$9$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$9$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$9$p)));
																	}
																	
																	// Recorded the probability of reaching sample task 104 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													
													// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
													// the output of Sample task 87.
													if(!true) {
														// Enumerating the possible outputs of Categorical 78.
														for(int index$sample84$68 = 0; index$sample84$68 < 2; index$sample84$68 += 1) {
															int distributionTempVariable$y$70 = index$sample84$68;
															
															// Update the probability of sampling this value from the distribution value.
															double cv$probabilitySample84Value69 = (1.0 * distribution$sample84[index$sample84$68]);
															double traceTempVariable$p$73_1 = 0.8;
															if((0 == distributionTempVariable$y$70)) {
																if((1 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$12$p;
																			{
																				cv$temp$12$p = traceTempVariable$p$73_1;
																			}
																			
																			// Record the probability of sample task 104 generating output with current configuration.
																			if(((Math.log(cv$probabilitySample84Value69) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$12$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value69) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$12$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value69) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$12$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value69) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$12$p)))) + 1)) + (Math.log(cv$probabilitySample84Value69) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$12$p)));
																			}
																			
																			// Recorded the probability of reaching sample task 104 with the current configuration.
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value69);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$72_1 = 0.8;
													if((0 == distributionTempVariable$y$25)) {
														if((1 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$11$p;
																	{
																		cv$temp$11$p = traceTempVariable$p$72_1;
																	}
																	
																	// Record the probability of sample task 104 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$11$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$11$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$11$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$11$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$11$p)));
																	}
																	
																	// Recorded the probability of reaching sample task 104 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													
													// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
													// the output of Sample task 87.
													if(!true) {
														// Enumerating the possible outputs of Categorical 78.
														for(int index$sample84$75 = 0; index$sample84$75 < 2; index$sample84$75 += 1) {
															int distributionTempVariable$y$77 = index$sample84$75;
															
															// Update the probability of sampling this value from the distribution value.
															double cv$probabilitySample84Value76 = (1.0 * distribution$sample84[index$sample84$75]);
															double traceTempVariable$p$80_1 = 0.4;
															if((1 == distributionTempVariable$y$77)) {
																if((0 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$14$p;
																			{
																				cv$temp$14$p = traceTempVariable$p$80_1;
																			}
																			
																			// Record the probability of sample task 104 generating output with current configuration.
																			if(((Math.log(cv$probabilitySample84Value76) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$14$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value76) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$14$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value76) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$14$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value76) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$14$p)))) + 1)) + (Math.log(cv$probabilitySample84Value76) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$14$p)));
																			}
																			
																			// Recorded the probability of reaching sample task 104 with the current configuration.
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value76);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$79_1 = 0.4;
													if((1 == distributionTempVariable$y$25)) {
														if((0 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$13$p;
																	{
																		cv$temp$13$p = traceTempVariable$p$79_1;
																	}
																	
																	// Record the probability of sample task 104 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$13$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$13$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$13$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$13$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$13$p)));
																	}
																	
																	// Recorded the probability of reaching sample task 104 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													
													// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
													// the output of Sample task 87.
													if(!true) {
														// Enumerating the possible outputs of Categorical 78.
														for(int index$sample84$82 = 0; index$sample84$82 < 2; index$sample84$82 += 1) {
															int distributionTempVariable$y$84 = index$sample84$82;
															
															// Update the probability of sampling this value from the distribution value.
															double cv$probabilitySample84Value83 = (1.0 * distribution$sample84[index$sample84$82]);
															double traceTempVariable$p$87_1 = 0.2;
															if((1 == distributionTempVariable$y$84)) {
																if((1 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$16$p;
																			{
																				cv$temp$16$p = traceTempVariable$p$87_1;
																			}
																			
																			// Record the probability of sample task 104 generating output with current configuration.
																			if(((Math.log(cv$probabilitySample84Value83) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$16$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value83) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$16$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value83) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$16$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value83) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$16$p)))) + 1)) + (Math.log(cv$probabilitySample84Value83) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$16$p)));
																			}
																			
																			// Recorded the probability of reaching sample task 104 with the current configuration.
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value83);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$86_1 = 0.2;
													if((1 == distributionTempVariable$y$25)) {
														if((1 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$15$p;
																	{
																		cv$temp$15$p = traceTempVariable$p$86_1;
																	}
																	
																	// Record the probability of sample task 104 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$15$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$15$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$15$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$15$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$15$p)));
																	}
																	
																	// Recorded the probability of reaching sample task 104 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
													
													// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
													// the output of Sample task 87.
													if(!true) {
														// Enumerating the possible outputs of Categorical 78.
														for(int index$sample84$89 = 0; index$sample84$89 < 2; index$sample84$89 += 1) {
															int distributionTempVariable$y$91 = index$sample84$89;
															
															// Update the probability of sampling this value from the distribution value.
															double cv$probabilitySample84Value90 = (1.0 * distribution$sample84[index$sample84$89]);
															double traceTempVariable$p$94_1 = 0.6;
															if((1 == distributionTempVariable$y$91)) {
																if((2 == traceTempVariable$i$43_1)) {
																	{
																		{
																			double cv$temp$18$p;
																			{
																				cv$temp$18$p = traceTempVariable$p$94_1;
																			}
																			
																			// Record the probability of sample task 104 generating output with current configuration.
																			if(((Math.log(cv$probabilitySample84Value90) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$18$p)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value90) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$18$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value90) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$18$p));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value90) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$18$p)))) + 1)) + (Math.log(cv$probabilitySample84Value90) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$18$p)));
																			}
																			
																			// Recorded the probability of reaching sample task 104 with the current configuration.
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value90);
																		}
																	}
																}
															}
														}
													}
													double traceTempVariable$p$93_1 = 0.6;
													if((1 == distributionTempVariable$y$25)) {
														if((2 == traceTempVariable$i$43_1)) {
															{
																{
																	double cv$temp$17$p;
																	{
																		cv$temp$17$p = traceTempVariable$p$93_1;
																	}
																	
																	// Record the probability of sample task 104 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$17$p)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$17$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$17$p));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$17$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$17$p)));
																	}
																	
																	// Recorded the probability of reaching sample task 104 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
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
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Categorical 81.
			if(fixedFlag$sample84) {
				if((1 == y)) {
					if((1 == y)) {
						if((1 == y)) {
							// Record the reached probability density.
							cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
							double[] cv$temp$2$var80;
							{
								// Constructing a random variable input for use later.
								double[] var80 = a[y];
								cv$temp$2$var80 = var80;
							}
							
							// An accumulator to allow the value for each distribution to be constructed before
							// it is added to the index probabilities.
							double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$2$var80.length))?Math.log(cv$temp$2$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
							
							// Processing random variable 85.
							{
								{
									int traceTempVariable$i$44_1 = cv$currentValue;
									
									// Processing sample task 104 of consumer random variable null.
									{
										for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
												// the output of Sample task 87.
												double traceTempVariable$p$96_1 = 0.2;
												if((0 == y)) {
													if((0 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$19$p;
																{
																	cv$temp$19$p = traceTempVariable$p$96_1;
																}
																
																// Record the probability of sample task 104 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$19$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$19$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$19$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$19$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$19$p)));
																}
																
																// Recorded the probability of reaching sample task 104 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
												}
												
												// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
												// the output of Sample task 87.
												double traceTempVariable$p$98_1 = 0.8;
												if((0 == y)) {
													if((1 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$20$p;
																{
																	cv$temp$20$p = traceTempVariable$p$98_1;
																}
																
																// Record the probability of sample task 104 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$20$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$20$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$20$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$20$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$20$p)));
																}
																
																// Recorded the probability of reaching sample task 104 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
												}
												
												// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
												// the output of Sample task 87.
												double traceTempVariable$p$100_1 = 0.4;
												if((1 == y)) {
													if((0 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$21$p;
																{
																	cv$temp$21$p = traceTempVariable$p$100_1;
																}
																
																// Record the probability of sample task 104 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$21$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$21$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$21$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$21$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$21$p)));
																}
																
																// Recorded the probability of reaching sample task 104 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
												}
												
												// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
												// the output of Sample task 87.
												double traceTempVariable$p$102_1 = 0.2;
												if((1 == y)) {
													if((1 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$22$p;
																{
																	cv$temp$22$p = traceTempVariable$p$102_1;
																}
																
																// Record the probability of sample task 104 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$22$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$22$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$22$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$22$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$22$p)));
																}
																
																// Recorded the probability of reaching sample task 104 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
												}
												
												// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
												// the output of Sample task 87.
												double traceTempVariable$p$104_1 = 0.6;
												if((1 == y)) {
													if((2 == traceTempVariable$i$44_1)) {
														{
															{
																double cv$temp$23$p;
																{
																	cv$temp$23$p = traceTempVariable$p$104_1;
																}
																
																// Record the probability of sample task 104 generating output with current configuration.
																if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$23$p)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$23$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	// If the second value is -infinity.
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$23$p));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$23$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$23$p)));
																}
																
																// Recorded the probability of reaching sample task 104 with the current configuration.
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
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
					}
				}
			} else {
				if(true) {
					// Enumerating the possible outputs of Categorical 78.
					for(int index$sample84$32 = 0; index$sample84$32 < 2; index$sample84$32 += 1) {
						int distributionTempVariable$y$34 = index$sample84$32;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample84Value33 = (1.0 * distribution$sample84[index$sample84$32]);
						if((1 == distributionTempVariable$y$34)) {
							if((1 == distributionTempVariable$y$34)) {
								if((1 == distributionTempVariable$y$34)) {
									// Record the reached probability density.
									cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample84Value33);
									double[] cv$temp$3$var80;
									{
										// Constructing a random variable input for use later.
										double[] var80 = a[distributionTempVariable$y$34];
										cv$temp$3$var80 = var80;
									}
									
									// An accumulator to allow the value for each distribution to be constructed before
									// it is added to the index probabilities.
									double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample84Value33) + (((0.0 <= cv$currentValue) && (cv$currentValue < cv$temp$3$var80.length))?Math.log(cv$temp$3$var80[cv$currentValue]):Double.NEGATIVE_INFINITY));
									
									// Processing random variable 85.
									{
										{
											int traceTempVariable$i$45_1 = cv$currentValue;
											
											// Processing sample task 104 of consumer random variable null.
											{
												for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
													// Set an accumulator to sum the probabilities for each possible configuration of
													// inputs.
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													
													// Set an accumulator to record the consumer distributions not seen. Initially set
													// to 1 as seen values will be deducted from this value.
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
														// the output of Sample task 87.
														if(!true) {
															// Enumerating the possible outputs of Categorical 78.
															for(int index$sample84$106 = 0; index$sample84$106 < 2; index$sample84$106 += 1) {
																int distributionTempVariable$y$108 = index$sample84$106;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample84Value107 = (1.0 * distribution$sample84[index$sample84$106]);
																double traceTempVariable$p$111_1 = 0.2;
																if((0 == distributionTempVariable$y$108)) {
																	if((0 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$25$p;
																				{
																					cv$temp$25$p = traceTempVariable$p$111_1;
																				}
																				
																				// Record the probability of sample task 104 generating output with current configuration.
																				if(((Math.log(cv$probabilitySample84Value107) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$25$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value107) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$25$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value107) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$25$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value107) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$25$p)))) + 1)) + (Math.log(cv$probabilitySample84Value107) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$25$p)));
																				}
																				
																				// Recorded the probability of reaching sample task 104 with the current configuration.
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value107);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$110_1 = 0.2;
														if((0 == distributionTempVariable$y$34)) {
															if((0 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$24$p;
																		{
																			cv$temp$24$p = traceTempVariable$p$110_1;
																		}
																		
																		// Record the probability of sample task 104 generating output with current configuration.
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$24$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$24$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$24$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$24$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$24$p)));
																		}
																		
																		// Recorded the probability of reaching sample task 104 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
														
														// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
														// the output of Sample task 87.
														if(!true) {
															// Enumerating the possible outputs of Categorical 78.
															for(int index$sample84$113 = 0; index$sample84$113 < 2; index$sample84$113 += 1) {
																int distributionTempVariable$y$115 = index$sample84$113;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample84Value114 = (1.0 * distribution$sample84[index$sample84$113]);
																double traceTempVariable$p$118_1 = 0.8;
																if((0 == distributionTempVariable$y$115)) {
																	if((1 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$27$p;
																				{
																					cv$temp$27$p = traceTempVariable$p$118_1;
																				}
																				
																				// Record the probability of sample task 104 generating output with current configuration.
																				if(((Math.log(cv$probabilitySample84Value114) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$27$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value114) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$27$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value114) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$27$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value114) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$27$p)))) + 1)) + (Math.log(cv$probabilitySample84Value114) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$27$p)));
																				}
																				
																				// Recorded the probability of reaching sample task 104 with the current configuration.
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value114);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$117_1 = 0.8;
														if((0 == distributionTempVariable$y$34)) {
															if((1 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$26$p;
																		{
																			cv$temp$26$p = traceTempVariable$p$117_1;
																		}
																		
																		// Record the probability of sample task 104 generating output with current configuration.
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$26$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$26$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$26$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$26$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$26$p)));
																		}
																		
																		// Recorded the probability of reaching sample task 104 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
														
														// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
														// the output of Sample task 87.
														if(!true) {
															// Enumerating the possible outputs of Categorical 78.
															for(int index$sample84$120 = 0; index$sample84$120 < 2; index$sample84$120 += 1) {
																int distributionTempVariable$y$122 = index$sample84$120;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample84Value121 = (1.0 * distribution$sample84[index$sample84$120]);
																double traceTempVariable$p$125_1 = 0.4;
																if((1 == distributionTempVariable$y$122)) {
																	if((0 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$29$p;
																				{
																					cv$temp$29$p = traceTempVariable$p$125_1;
																				}
																				
																				// Record the probability of sample task 104 generating output with current configuration.
																				if(((Math.log(cv$probabilitySample84Value121) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$29$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value121) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$29$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value121) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$29$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value121) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$29$p)))) + 1)) + (Math.log(cv$probabilitySample84Value121) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$29$p)));
																				}
																				
																				// Recorded the probability of reaching sample task 104 with the current configuration.
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value121);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$124_1 = 0.4;
														if((1 == distributionTempVariable$y$34)) {
															if((0 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$28$p;
																		{
																			cv$temp$28$p = traceTempVariable$p$124_1;
																		}
																		
																		// Record the probability of sample task 104 generating output with current configuration.
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$28$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$28$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$28$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$28$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$28$p)));
																		}
																		
																		// Recorded the probability of reaching sample task 104 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
														
														// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
														// the output of Sample task 87.
														if(!true) {
															// Enumerating the possible outputs of Categorical 78.
															for(int index$sample84$127 = 0; index$sample84$127 < 2; index$sample84$127 += 1) {
																int distributionTempVariable$y$129 = index$sample84$127;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample84Value128 = (1.0 * distribution$sample84[index$sample84$127]);
																double traceTempVariable$p$132_1 = 0.2;
																if((1 == distributionTempVariable$y$129)) {
																	if((1 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$31$p;
																				{
																					cv$temp$31$p = traceTempVariable$p$132_1;
																				}
																				
																				// Record the probability of sample task 104 generating output with current configuration.
																				if(((Math.log(cv$probabilitySample84Value128) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$31$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value128) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$31$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value128) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$31$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value128) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$31$p)))) + 1)) + (Math.log(cv$probabilitySample84Value128) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$31$p)));
																				}
																				
																				// Recorded the probability of reaching sample task 104 with the current configuration.
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value128);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$131_1 = 0.2;
														if((1 == distributionTempVariable$y$34)) {
															if((1 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$30$p;
																		{
																			cv$temp$30$p = traceTempVariable$p$131_1;
																		}
																		
																		// Record the probability of sample task 104 generating output with current configuration.
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$30$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$30$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$30$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$30$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$30$p)));
																		}
																		
																		// Recorded the probability of reaching sample task 104 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
																}
															}
														}
														
														// Enumerating the possible arguments for the variable Bernoulli 85 which is consuming
														// the output of Sample task 87.
														if(!true) {
															// Enumerating the possible outputs of Categorical 78.
															for(int index$sample84$134 = 0; index$sample84$134 < 2; index$sample84$134 += 1) {
																int distributionTempVariable$y$136 = index$sample84$134;
																
																// Update the probability of sampling this value from the distribution value.
																double cv$probabilitySample84Value135 = (1.0 * distribution$sample84[index$sample84$134]);
																double traceTempVariable$p$139_1 = 0.6;
																if((1 == distributionTempVariable$y$136)) {
																	if((2 == traceTempVariable$i$45_1)) {
																		{
																			{
																				double cv$temp$33$p;
																				{
																					cv$temp$33$p = traceTempVariable$p$139_1;
																				}
																				
																				// Record the probability of sample task 104 generating output with current configuration.
																				if(((Math.log(cv$probabilitySample84Value135) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$33$p)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample84Value135) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$33$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample84Value135) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$33$p));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample84Value135) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$33$p)))) + 1)) + (Math.log(cv$probabilitySample84Value135) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$33$p)));
																				}
																				
																				// Recorded the probability of reaching sample task 104 with the current configuration.
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample84Value135);
																			}
																		}
																	}
																}
															}
														}
														double traceTempVariable$p$138_1 = 0.6;
														if((1 == distributionTempVariable$y$34)) {
															if((2 == traceTempVariable$i$45_1)) {
																{
																	{
																		double cv$temp$32$p;
																		{
																			cv$temp$32$p = traceTempVariable$p$138_1;
																		}
																		
																		// Record the probability of sample task 104 generating output with current configuration.
																		if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$32$p)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$32$p)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			// If the second value is -infinity.
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$32$p));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$32$p)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(obs[var97], cv$temp$32$p)));
																		}
																		
																		// Recorded the probability of reaching sample task 104 with the current configuration.
																		cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																	}
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
							}
						}
					}
				}
			}
			
			// Save the calculated index value into the array of index value probabilities
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		
		// The sum of all the probabilities in log space
		double cv$logSum = 0.0;
		
		// Sum all the values
		{
			// Initialise the max to the first element.
			double cv$lseMax = cv$stateProbabilityLocal[0];
			
			// Find max value.
			for(int cv$lseIndex = 1; cv$lseIndex < cv$noStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			
			// If the maximum value is -infinity return -infinity.
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			
			// Sum the values in the array.
			else {
				// Initialise the sum of the array elements
				double cv$lseSum = 0.0;
				
				// Offset values, move to normal space, and sum.
				for(int cv$lseIndex = 0; cv$lseIndex < cv$noStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				
				// Increment the value of the target, moving the value back into log space.
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		
		// If all the sum is zero, just share the probability evenly.
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$noStates);
		} else {
			// Normalize log space values and move to normal space
			for(int cv$indexName = 0; cv$indexName < cv$noStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		
		// Set array values that are not computed for the input to negative infinity.
		for(int cv$indexName = cv$noStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		
		// Write out the new value of the sample.
		i = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal);
		
		// Guards to ensure that p is only updated when there is a valid path.
		{
			{
				// Write out the new sample value.
				p = b[y][i];
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var79$stateProbabilityGlobal
		{
			// Allocation of cv$var79$stateProbabilityGlobal for single threaded execution
			cv$var79$stateProbabilityGlobal = new double[2];
		}
		
		// Constructor for cv$var82$stateProbabilityGlobal
		{
			// Variable to record the maximum value of Task Get 85. Initially set to the value
			// of putTask 19.
			int cv$var35$max = 2;
			
			// Test if the input to putTask 37 is larger than the current values.
			cv$var35$max = Math.max(cv$var35$max, 3);
			
			// Allocation of cv$var82$stateProbabilityGlobal for single threaded execution
			cv$var82$stateProbabilityGlobal = new double[cv$var35$max];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for a
		{
			a = new double[2][];
			a[0] = new double[2];
			a[1] = new double[3];
		}
		
		// Constructor for b
		{
			b = new double[2][];
			b[0] = new double[2];
			b[1] = new double[3];
		}
		
		// Constructor for c
		{
			c = new double[2];
		}
		
		// If obs has not been set already allocate space.
		if(!setFlag$obs) {
			// Constructor for obs
			{
				obs = new boolean[length$obs_measured];
			}
		}
		
		// Constructor for distribution$sample84
		{
			distribution$sample84 = new double[2];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample84)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if(!(fixedFlag$sample84 && fixedFlag$sample87))
			p = b[y][i];
		for(int var97 = 0; var97 < length$obs_measured; var97 += 1) {
			if(!fixedFlag$sample104)
				obs[var97] = DistributionSampling.sampleBernoulli(RNG$, p);
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are calculated and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		// Create local copy of variable probabilities.
		double[] cv$distribution$sample84 = distribution$sample84;
		for(int index$var78 = 0; index$var78 < 2; index$var78 += 1) {
			// Probability for this value
			double cv$value = (((0.0 <= index$var78) && (index$var78 < c.length))?c[index$var78]:0.0);
			if(!fixedFlag$sample84)
				// Save the probability of each value
				cv$distribution$sample84[index$var78] = cv$value;
		}
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample84)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if(!(fixedFlag$sample84 && fixedFlag$sample87))
			p = b[y][i];
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample84)
				sample84();
			if(!fixedFlag$sample87)
				sample87();
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!fixedFlag$sample87)
				sample87();
			if(!fixedFlag$sample84)
				sample84();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		double[] var7 = a[0];
		var7[0] = 0.4;
		var7[1] = 0.6;
		double[] var20 = a[1];
		var20[0] = 0.2;
		var20[1] = 0.3;
		var20[2] = 0.5;
		double[] var39 = b[0];
		var39[0] = 0.2;
		var39[1] = 0.8;
		double[] var52 = b[1];
		var52[0] = 0.4;
		var52[1] = 0.2;
		var52[2] = 0.6;
		c[0] = 0.35;
		c[1] = 0.65;
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
		logProbability$var78 = 0.0;
		logProbability$p = 0.0;
		if(!fixedProbFlag$sample84)
			logProbability$y = 0.0;
		logProbability$var81 = 0.0;
		if(!fixedProbFlag$sample87)
			logProbability$i = 0.0;
		logProbability$var85 = 0.0;
		logProbability$obs = 0.0;
		if(!fixedProbFlag$sample104)
			logProbability$var98 = 0.0;
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
		if(fixedFlag$sample87)
			logProbabilityValue$sample87();
		logProbabilityValue$sample104();
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
		logProbabilityDistribution$sample84();
		logProbabilityDistribution$sample87();
		logProbabilityDistribution$sample104();
	}

	// Method to calculate the probabilities of all the samples in the model including
	// those generating fixed data. In the process probabilities for all the random variables
	// and for the model as a whole will be calculated. This model only uses values.
	@Override
	public final void logModelProbabilitiesVal() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Calculate the probabilities for each sample task in the model, generating probabilities
		// for the random variables and whole model in the process using values only.
		logProbabilityValue$sample84();
		logProbabilityValue$sample87();
		logProbabilityValue$sample104();
	}

	// Method to generate a random state of the model including random outputs, and then
	// to calculate the probability of this random state.
	@Override
	public final void logProbabilityGeneration() {
		// Generate sample values for every call to sample in the model.
		if(!fixedFlag$sample84)
			y = DistributionSampling.sampleCategorical(RNG$, c);
		if(!fixedFlag$sample87)
			i = DistributionSampling.sampleCategorical(RNG$, a[y]);
		if(!(fixedFlag$sample84 && fixedFlag$sample87))
			p = b[y][i];
		
		// Calculate the probabilities for every sample task in the model. These values are
		// then used to calculate the probabilities of random variables and the model as a
		// whole.
		logModelProbabilitiesVal();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propogateObservedValues() {
		// Deep copy between arrays
		boolean[] cv$source1 = obs_measured;
		boolean[] cv$target1 = obs;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task.
	@Override
	public final void setIntermediates() {
		if(true)
			p = b[y][i];
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2025, Oracle and/or its affiliates\n *\n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n \n package org.sandwood.compiler.tests.parser;\n\npublic model RaggedArray2(boolean[] obs_measured) {\n    double[][] a = {{0.4, 0.6}, {0.2, 0.3, 0.5}};\n    double[][] b = {{0.2, 0.8}, {0.4, 0.2, 0.6}};\n    double[] c = { 0.35, 0.65 };\n    int y = categorical(c).sampleDistribution();\n    int i = categorical(a[y]).sample();\n    double p = b[y][i];\n    boolean[] obs = bernoulli(p).sample(obs_measured.length);\n    obs.observe(obs_measured);\n}";
	}
}