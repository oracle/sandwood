package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest7$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DistributionTest7$CoreInterface {
	
	// Declare the variables for the model.
	private double[] bias;
	private int cat;
	private double[] cv$var31$stateProbabilityGlobal;
	private double[] cv$var43$stateProbabilityGlobal;
	private double data;
	private double[] distribution$sample31;
	private boolean fixedFlag$sample31 = false;
	private boolean fixedFlag$sample45 = false;
	private boolean fixedProbFlag$sample31 = false;
	private boolean fixedProbFlag$sample45 = false;
	private boolean fixedProbFlag$sample51 = false;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$cat;
	private double logProbability$data;
	private double logProbability$result;
	private double logProbability$sample45;
	private double logProbability$var43;
	private double observedData;
	private double[] prob;
	private int result;
	private boolean system$gibbsForward = true;
	private int var43;

	public DistributionTest7$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	// Getter for bias.
	@Override
	public final double[] get$bias() {
		return bias;
	}

	// Getter for cat.
	@Override
	public final int get$cat() {
		return cat;
	}

	// Setter for cat.
	@Override
	public final void set$cat(int cv$value) {
		// Set flags for all the side effects of cat including if probabilities need to be
		// updated.
		cat = cv$value;
		
		// Unset the fixed probability flag for sample 31 as it depends on cat.
		fixedProbFlag$sample31 = false;
		
		// Unset the fixed probability flag for sample 45 as it depends on cat.
		fixedProbFlag$sample45 = false;
		
		// Unset the fixed probability flag for sample 51 as it depends on cat.
		fixedProbFlag$sample51 = false;
	}

	// Getter for data.
	@Override
	public final double get$data() {
		return data;
	}

	// Getter for distribution$sample31.
	@Override
	public final double[] get$distribution$sample31() {
		return distribution$sample31;
	}

	// Setter for distribution$sample31.
	@Override
	public final void set$distribution$sample31(double[] cv$value) {
		// Set distribution$sample31
		distribution$sample31 = cv$value;
	}

	// Getter for fixedFlag$sample31.
	@Override
	public final boolean get$fixedFlag$sample31() {
		return fixedFlag$sample31;
	}

	// Setter for fixedFlag$sample31.
	@Override
	public final void set$fixedFlag$sample31(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample31 including if probabilities
		// need to be updated.
		fixedFlag$sample31 = cv$value;
		
		// Should the probability of sample 31 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample31 = (fixedFlag$sample31 && fixedProbFlag$sample31);
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample45 = (fixedFlag$sample31 && fixedProbFlag$sample45);
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedProbFlag$sample51);
	}

	// Getter for fixedFlag$sample45.
	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	// Setter for fixedFlag$sample45.
	@Override
	public final void set$fixedFlag$sample45(boolean cv$value) {
		// Set flags for all the side effects of fixedFlag$sample45 including if probabilities
		// need to be updated.
		fixedFlag$sample45 = cv$value;
		
		// Should the probability of sample 45 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		
		// Should the probability of sample 51 be set to fixed. This will only every change
		// the flag to false.
		fixedProbFlag$sample51 = (fixedFlag$sample45 && fixedProbFlag$sample51);
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

	// Getter for logProbability$cat.
	@Override
	public final double get$logProbability$cat() {
		return logProbability$cat;
	}

	// Getter for logProbability$data.
	@Override
	public final double get$logProbability$data() {
		return logProbability$data;
	}

	// Getter for logProbability$result.
	@Override
	public final double get$logProbability$result() {
		return logProbability$result;
	}

	// Getter for observedData.
	@Override
	public final double get$observedData() {
		return observedData;
	}

	// Setter for observedData.
	@Override
	public final void set$observedData(double cv$value) {
		observedData = cv$value;
	}

	// Getter for prob.
	@Override
	public final double[] get$prob() {
		return prob;
	}

	// Getter for result.
	@Override
	public final int get$result() {
		return result;
	}

	// Getter for var43.
	@Override
	public final int get$var43() {
		return var43;
	}

	// Setter for var43.
	@Override
	public final void set$var43(int cv$value) {
		// Set flags for all the side effects of var43 including if probabilities need to
		// be updated.
		var43 = cv$value;
		
		// Unset the fixed probability flag for sample 45 as it depends on var43.
		fixedProbFlag$sample45 = false;
		
		// Unset the fixed probability flag for sample 51 as it depends on var43.
		fixedProbFlag$sample51 = false;
	}

	// Calculate the probability of the samples represented by sample31 using probability
	// distributions.
	private final void logProbabilityDistribution$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
			// Update the probability if the distribution is fixed to a specific value. If it
			// is not the value is implicitly log(1.0) so has no effect.
			if(fixedFlag$sample31) {
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
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = cat;
						{
							{
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 3))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
								
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
				
				// Store the sample task probability
				logProbability$cat = cv$sampleProbability;
				
				// Add probability to model
				logProbability$$model = (logProbability$$model + cv$accumulator);
				
				// If this value is fixed, add it to the probability of this model producing the fixed
				// values
				if(fixedFlag$sample31)
					logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
				
				// Now the probability is calculated store if it can be cached or if it needs to be
				// recalculated next time.
				fixedProbFlag$sample31 = fixedFlag$sample31;
			}
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$cat;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample45 using probability
	// distributions.
	private final void logProbabilityDistribution$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample45) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!(cat == 1)) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				
				// Look for paths between the variable and the sample task 45 including any distribution
				// values.
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = var43;
						
						// Enumerating the possible arguments for Binomial 42.
						if(fixedFlag$sample31) {
							{
								{
									double traceTempVariable$var40$7_1 = 0.2;
									if((0 == cat)) {
										{
											double var40 = traceTempVariable$var40$7_1;
											int var41 = 10;
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
											
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
								// Enumerating the possible outputs of Categorical 30.
								for(int index$sample31$3 = 0; index$sample31$3 < 3; index$sample31$3 += 1) {
									int distributionTempVariable$cat$5 = index$sample31$3;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample31Value4 = (1.0 * distribution$sample31[index$sample31$3]);
									{
										{
											double traceTempVariable$var40$8_1 = 0.2;
											if((0 == distributionTempVariable$cat$5)) {
												{
													double var40 = traceTempVariable$var40$8_1;
													int var41 = 10;
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value4) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value4);
												}
											}
										}
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Binomial 42.
						if(fixedFlag$sample31) {
							{
								{
									double traceTempVariable$var40$14_1 = 0.3;
									if((1 == cat)) {
										{
											double var40 = traceTempVariable$var40$14_1;
											int var41 = 10;
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
											
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
								// Enumerating the possible outputs of Categorical 30.
								for(int index$sample31$10 = 0; index$sample31$10 < 3; index$sample31$10 += 1) {
									int distributionTempVariable$cat$12 = index$sample31$10;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample31Value11 = (1.0 * distribution$sample31[index$sample31$10]);
									{
										{
											double traceTempVariable$var40$15_1 = 0.3;
											if((1 == distributionTempVariable$cat$12)) {
												{
													double var40 = traceTempVariable$var40$15_1;
													int var41 = 10;
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value11) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value11);
												}
											}
										}
									}
								}
							}
						}
						
						// Enumerating the possible arguments for Binomial 42.
						if(fixedFlag$sample31) {
							{
								{
									double traceTempVariable$var40$21_1 = 0.5;
									if((2 == cat)) {
										{
											double var40 = traceTempVariable$var40$21_1;
											int var41 = 10;
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
											
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
								// Enumerating the possible outputs of Categorical 30.
								for(int index$sample31$17 = 0; index$sample31$17 < 3; index$sample31$17 += 1) {
									int distributionTempVariable$cat$19 = index$sample31$17;
									
									// Update the probability of sampling this value from the distribution value.
									double cv$probabilitySample31Value18 = (1.0 * distribution$sample31[index$sample31$17]);
									{
										{
											double traceTempVariable$var40$22_1 = 0.5;
											if((2 == distributionTempVariable$cat$19)) {
												{
													double var40 = traceTempVariable$var40$22_1;
													int var41 = 10;
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value18) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value18);
												}
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
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				logProbability$sample45 = cv$sampleProbability;
				
				// Guard to ensure that result is only updated once for this probability.
				boolean cv$guard$result = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(!(cat == 1)) {
							// Make sure all the inputs have been fixed so the variable is not a distribution.
							if((fixedFlag$sample31 && fixedFlag$sample45)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$result) {
									// Set the guard so the update is only applied once.
									cv$guard$result = true;
									
									// Update the variable probability
									logProbability$result = (logProbability$result + cv$sampleProbability);
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample31);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!(cat == 1)) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample45;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Guard to ensure that result is only updated once for this probability.
				boolean cv$guard$result = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(!(cat == 1)) {
							// Make sure all the inputs have been fixed so the variable is not a distribution.
							if((fixedFlag$sample31 && fixedFlag$sample45)) {
								// If the probability of the variable has not already been updated
								if(!cv$guard$result) {
									// Set the guard so the update is only applied once.
									cv$guard$result = true;
									
									// Update the variable probability
									logProbability$result = (logProbability$result + cv$sampleValue);
								}
							}
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample51 using probability
	// distributions.
	private final void logProbabilityDistribution$sample51() {
		// Determine if we need to calculate the values for sample task 51 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample51) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			
			// Look for paths between the variable and the sample task 51 including any distribution
			// values.
			{
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = data;
					
					// Enumerating the possible arguments for Gaussian 48.
					if(fixedFlag$sample31) {
						{
							{
								if(!(cat == 1)) {
									int traceTempVariable$result$7_1 = var43;
									{
										{
											double var47 = (double)traceTempVariable$result$7_1;
											double var46 = (double)cat;
											
											// Store the value of the function call, so the function call is only made once.
											double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
											
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
						}
					} else {
						if(true) {
							// Enumerating the possible outputs of Categorical 30.
							for(int index$sample31$3 = 0; index$sample31$3 < 3; index$sample31$3 += 1) {
								int distributionTempVariable$cat$5 = index$sample31$3;
								
								// Update the probability of sampling this value from the distribution value.
								double cv$probabilitySample31Value4 = (1.0 * distribution$sample31[index$sample31$3]);
								{
									{
										if(!(distributionTempVariable$cat$5 == 1)) {
											int traceTempVariable$result$8_1 = var43;
											{
												{
													double var47 = (double)traceTempVariable$result$8_1;
													double var46 = (double)distributionTempVariable$cat$5;
													
													// Store the value of the function call, so the function call is only made once.
													double cv$weightedProbability = (Math.log(cv$probabilitySample31Value4) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
													
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
													cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value4);
												}
											}
											if(!true) {
												// Enumerating the possible outputs of Categorical 30.
												for(int index$sample31$11 = 0; index$sample31$11 < 3; index$sample31$11 += 1) {
													int distributionTempVariable$cat$13 = index$sample31$11;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample31Value12 = (cv$probabilitySample31Value4 * distribution$sample31[index$sample31$11]);
													{
														{
															double var47 = (double)traceTempVariable$result$8_1;
															double var46 = (double)distributionTempVariable$cat$13;
															
															// Store the value of the function call, so the function call is only made once.
															double cv$weightedProbability = (Math.log(cv$probabilitySample31Value12) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
															
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
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample31Value12);
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
			
			// Store the sample task probability
			logProbability$data = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedFlag$sample45);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$data;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample31 using sampled
	// values.
	private final void logProbabilityValue$sample31() {
		// Determine if we need to calculate the values for sample task 31 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample31) {
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
				{
					// The sample value to calculate the probability of generating
					int cv$sampleValue = cat;
					{
						{
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 3))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							
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
			
			// Store the sample task probability
			logProbability$cat = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample31 = fixedFlag$sample31;
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$cat;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample31)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Calculate the probability of the samples represented by sample45 using sampled
	// values.
	private final void logProbabilityValue$sample45() {
		// Determine if we need to calculate the values for sample task 45 or if we should
		// just use cached values.
		if(!fixedProbFlag$sample45) {
			// Generating probabilities for sample task
			// Accumulator for probabilities of instances of the random variable
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!(cat == 1)) {
				// Accumulator for sample probabilities for a specific instance of the random variable.
				double cv$sampleAccumulator = 0.0;
				
				// An accumulator for log probabilities.
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				
				// An accumulator for the distributed probability space covered.
				double cv$probabilityReached = 0.0;
				{
					{
						// The sample value to calculate the probability of generating
						int cv$sampleValue = var43;
						{
							{
								double var40 = bias[cat];
								int var41 = 10;
								
								// Store the value of the function call, so the function call is only made once.
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$sampleValue, var40, var41));
								
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
				if((cv$probabilityReached == 0.0))
					// Return negative infinity if no distribution probability space is reached.
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					// Scale the probability relative to the observed distribution space.
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				
				// Add the probability of this sample task to the sample task accumulator.
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				
				// Add the probability of this instance of the random variable to the probability
				// of all instances of the random variable.
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				
				// Store the sample task probability
				logProbability$sample45 = cv$sampleProbability;
				
				// Guard to ensure that result is only updated once for this probability.
				boolean cv$guard$result = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(!(cat == 1)) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$result) {
								// Set the guard so the update is only applied once.
								cv$guard$result = true;
								
								// Update the variable probability
								logProbability$result = (logProbability$result + cv$sampleProbability);
							}
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedFlag$sample31);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			
			// A guard to check if the sample value is ever reached.
			boolean cv$sampleReached = false;
			if(!(cat == 1)) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample45;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				
				// Record that the sample was reached.
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				
				// Guard to ensure that result is only updated once for this probability.
				boolean cv$guard$result = false;
				
				// Add probability to constructed variables that have guards, so need per sample probabilities
				// from the combined probability
				{
					{
						if(!(cat == 1)) {
							// If the probability of the variable has not already been updated
							if(!cv$guard$result) {
								// Set the guard so the update is only applied once.
								cv$guard$result = true;
								
								// Update the variable probability
								logProbability$result = (logProbability$result + cv$sampleValue);
							}
						}
					}
				}
			}
			
			// Update the variable probability
			logProbability$var43 = (logProbability$var43 + cv$accumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			
			// If this value is fixed, add it to the probability of this model producing the fixed
			// values
			if(fixedFlag$sample45)
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
			
			// Accumulator for sample probabilities for a specific instance of the random variable.
			double cv$sampleAccumulator = 0.0;
			
			// An accumulator for log probabilities.
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			
			// An accumulator for the distributed probability space covered.
			double cv$probabilityReached = 0.0;
			{
				{
					// The sample value to calculate the probability of generating
					double cv$sampleValue = data;
					{
						{
							double var47 = (double)result;
							double var46 = (double)cat;
							
							// Store the value of the function call, so the function call is only made once.
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
							
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
			
			// Store the sample task probability
			logProbability$data = cv$sampleProbability;
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			
			// Now the probability is calculated store if it can be cached or if it needs to be
			// recalculated next time.
			fixedProbFlag$sample51 = (fixedFlag$sample31 && fixedFlag$sample45);
		} else {
			// Using cached values.
			// 
			// Updating random variable and model probabilities using cached probabilities for
			// this sample
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$data;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			
			// Add probability to model
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 31 drawn from Categorical 30. Inference was performed using variable
	// marginalization.
	private final void sample31() {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			{
				// variable marginalization
				cv$numStates = Math.max(cv$numStates, 3);
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var31$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
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
					
					// An accumulator to allow the value for each distribution to be constructed before
					// it is added to the index probabilities.
					double cv$accumulatedProbabilities = (Math.log(1.0) + (((0.0 <= cv$currentValue) && (cv$currentValue < 3))?Math.log(prob[cv$currentValue]):Double.NEGATIVE_INFINITY));
					
					// Processing random variable 42.
					{
						{
							{
								if(!(cv$currentValue == 1)) {
									int traceTempVariable$cat$1_1 = cv$currentValue;
									
									// Processing sample task 45 of consumer random variable null.
									{
										{
											// Set an accumulator to sum the probabilities for each possible configuration of
											// inputs.
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											
											// Set an accumulator to record the consumer distributions not seen. Initially set
											// to 1 as seen values will be deducted from this value.
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												// Enumerating the possible arguments for the variable Binomial 42 which is consuming
												// the output of Sample task 31.
												{
													double traceTempVariable$var40$3_1 = 0.2;
													if((0 == traceTempVariable$cat$1_1)) {
														{
															{
																{
																	// Record the probability of sample task 45 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$3_1, 10)));
																	}
																	
																	// Recorded the probability of reaching sample task 45 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												
												// Enumerating the possible arguments for the variable Binomial 42 which is consuming
												// the output of Sample task 31.
												{
													double traceTempVariable$var40$4_1 = 0.3;
													if((1 == traceTempVariable$cat$1_1)) {
														{
															{
																{
																	// Record the probability of sample task 45 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$4_1, 10)));
																	}
																	
																	// Recorded the probability of reaching sample task 45 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												
												// Enumerating the possible arguments for the variable Binomial 42 which is consuming
												// the output of Sample task 31.
												{
													double traceTempVariable$var40$5_1 = 0.5;
													if((2 == traceTempVariable$cat$1_1)) {
														{
															{
																{
																	// Record the probability of sample task 45 generating output with current configuration.
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, traceTempVariable$var40$5_1, 10)));
																	}
																	
																	// Recorded the probability of reaching sample task 45 with the current configuration.
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
							}
						}
					}
					
					// Processing random variable 48.
					{
						{
							{
								int traceTempVariable$cat$9_1 = cv$currentValue;
								
								// Processing sample task 51 of consumer random variable null.
								{
									{
										// Set an accumulator to sum the probabilities for each possible configuration of
										// inputs.
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										
										// Set an accumulator to record the consumer distributions not seen. Initially set
										// to 1 as seen values will be deducted from this value.
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
											// the output of Sample task 31.
											{
												int traceTempVariable$cat$11_1 = cv$currentValue;
												{
													if(!(traceTempVariable$cat$11_1 == 1)) {
														int traceTempVariable$result$16_1 = var43;
														{
															{
																{
																	// Constructing a random variable input for use later.
																	double var47 = (double)traceTempVariable$result$16_1;
																	
																	// Constructing a random variable input for use later.
																	double var46 = (double)traceTempVariable$cat$11_1;
																	
																	// Record the probability of sample task 51 generating output with current configuration.
																	if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		// If the second value is -infinity.
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																	}
																	
																	// Recorded the probability of reaching sample task 51 with the current configuration.
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
											}
											if(!true) {
												// Enumerating the possible outputs of Categorical 30.
												for(int index$sample31$12 = 0; index$sample31$12 < 3; index$sample31$12 += 1) {
													int distributionTempVariable$cat$14 = index$sample31$12;
													
													// Update the probability of sampling this value from the distribution value.
													double cv$probabilitySample31Value13 = (1.0 * distribution$sample31[index$sample31$12]);
													{
														int traceTempVariable$cat$15_1 = distributionTempVariable$cat$14;
														{
															if(!(traceTempVariable$cat$15_1 == 1)) {
																int traceTempVariable$result$17_1 = var43;
																{
																	{
																		{
																			// Constructing a random variable input for use later.
																			double var47 = (double)traceTempVariable$result$17_1;
																			
																			// Constructing a random variable input for use later.
																			double var46 = (double)traceTempVariable$cat$15_1;
																			
																			// Record the probability of sample task 51 generating output with current configuration.
																			if(((Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				// If the second value is -infinity.
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value13) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																			}
																			
																			// Recorded the probability of reaching sample task 51 with the current configuration.
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value13);
																		}
																	}
																}
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
						}
					}
					
					// Processing conditional point47.
					{
						{
							{
								{
									{
										{
											int traceTempVariable$cat$21_1 = cv$currentValue;
											{
												if((traceTempVariable$cat$21_1 == 1)) {
													int traceTempVariable$result$26_1 = 5;
													
													// Processing sample task 51 of consumer random variable null.
													{
														{
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																	// the output of If task 47.
																	{
																		if(!(traceTempVariable$cat$21_1 == 1)) {
																			int traceTempVariable$result$30_1 = var43;
																			{
																				int traceTempVariable$cat$31_1 = cv$currentValue;
																				{
																					{
																						{
																							// Constructing a random variable input for use later.
																							double var47 = (double)traceTempVariable$result$30_1;
																							
																							// Constructing a random variable input for use later.
																							double var46 = (double)traceTempVariable$cat$31_1;
																							
																							// Record the probability of sample task 51 generating output with current configuration.
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																							}
																							
																							// Recorded the probability of reaching sample task 51 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																			if(!true) {
																				// Enumerating the possible outputs of Categorical 30.
																				for(int index$sample31$32 = 0; index$sample31$32 < 3; index$sample31$32 += 1) {
																					int distributionTempVariable$cat$34 = index$sample31$32;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample31Value33 = (1.0 * distribution$sample31[index$sample31$32]);
																					{
																						int traceTempVariable$cat$35_1 = distributionTempVariable$cat$34;
																						{
																							{
																								{
																									// Constructing a random variable input for use later.
																									double var47 = (double)traceTempVariable$result$30_1;
																									
																									// Constructing a random variable input for use later.
																									double var46 = (double)traceTempVariable$cat$35_1;
																									
																									// Record the probability of sample task 51 generating output with current configuration.
																									if(((Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value33) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									
																									// Recorded the probability of reaching sample task 51 with the current configuration.
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value33);
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
										if(!true) {
											// Enumerating the possible outputs of Categorical 30.
											for(int index$sample31$22 = 0; index$sample31$22 < 3; index$sample31$22 += 1) {
												int distributionTempVariable$cat$24 = index$sample31$22;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample31Value23 = (1.0 * distribution$sample31[index$sample31$22]);
												{
													int traceTempVariable$cat$25_1 = distributionTempVariable$cat$24;
													{
														if((traceTempVariable$cat$25_1 == 1)) {
															int traceTempVariable$result$27_1 = 5;
															
															// Processing sample task 51 of consumer random variable null.
															{
																{
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																			// the output of If task 47.
																			{
																				if(!(traceTempVariable$cat$25_1 == 1)) {
																					int traceTempVariable$result$36_1 = var43;
																					{
																						int traceTempVariable$cat$37_1 = cv$currentValue;
																						{
																							{
																								{
																									// Constructing a random variable input for use later.
																									double var47 = (double)traceTempVariable$result$36_1;
																									
																									// Constructing a random variable input for use later.
																									double var46 = (double)traceTempVariable$cat$37_1;
																									
																									// Record the probability of sample task 51 generating output with current configuration.
																									if(((Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									
																									// Recorded the probability of reaching sample task 51 with the current configuration.
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value23);
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$cat$38_1 = distributionTempVariable$cat$24;
																						{
																							{
																								{
																									// Constructing a random variable input for use later.
																									double var47 = (double)traceTempVariable$result$36_1;
																									
																									// Constructing a random variable input for use later.
																									double var46 = (double)traceTempVariable$cat$38_1;
																									
																									// Record the probability of sample task 51 generating output with current configuration.
																									if(((Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value23) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									
																									// Recorded the probability of reaching sample task 51 with the current configuration.
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value23);
																								}
																							}
																						}
																					}
																					if(!true) {
																						// Enumerating the possible outputs of Categorical 30.
																						for(int index$sample31$39 = 0; index$sample31$39 < 3; index$sample31$39 += 1) {
																							int distributionTempVariable$cat$41 = index$sample31$39;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample31Value40 = (cv$probabilitySample31Value23 * distribution$sample31[index$sample31$39]);
																							{
																								int traceTempVariable$cat$42_1 = distributionTempVariable$cat$41;
																								{
																									{
																										{
																											// Constructing a random variable input for use later.
																											double var47 = (double)traceTempVariable$result$36_1;
																											
																											// Constructing a random variable input for use later.
																											double var46 = (double)traceTempVariable$cat$42_1;
																											
																											// Record the probability of sample task 51 generating output with current configuration.
																											if(((Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value40) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																											}
																											
																											// Recorded the probability of reaching sample task 51 with the current configuration.
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value40);
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
									{
										{
											int traceTempVariable$cat$48_1 = cv$currentValue;
											{
												if(!(traceTempVariable$cat$48_1 == 1)) {
													int traceTempVariable$result$53_1 = var43;
													
													// Processing sample task 51 of consumer random variable null.
													{
														{
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																	// the output of If task 47.
																	{
																		if(!(traceTempVariable$cat$48_1 == 1)) {
																			int traceTempVariable$result$57_1 = var43;
																			{
																				int traceTempVariable$cat$58_1 = cv$currentValue;
																				{
																					{
																						{
																							// Constructing a random variable input for use later.
																							double var47 = (double)traceTempVariable$result$57_1;
																							
																							// Constructing a random variable input for use later.
																							double var46 = (double)traceTempVariable$cat$58_1;
																							
																							// Record the probability of sample task 51 generating output with current configuration.
																							if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																							else {
																								// If the second value is -infinity.
																								if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																									cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																								else
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																							}
																							
																							// Recorded the probability of reaching sample task 51 with the current configuration.
																							cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																						}
																					}
																				}
																			}
																			if(!true) {
																				// Enumerating the possible outputs of Categorical 30.
																				for(int index$sample31$59 = 0; index$sample31$59 < 3; index$sample31$59 += 1) {
																					int distributionTempVariable$cat$61 = index$sample31$59;
																					
																					// Update the probability of sampling this value from the distribution value.
																					double cv$probabilitySample31Value60 = (1.0 * distribution$sample31[index$sample31$59]);
																					{
																						int traceTempVariable$cat$62_1 = distributionTempVariable$cat$61;
																						{
																							{
																								{
																									// Constructing a random variable input for use later.
																									double var47 = (double)traceTempVariable$result$57_1;
																									
																									// Constructing a random variable input for use later.
																									double var46 = (double)traceTempVariable$cat$62_1;
																									
																									// Record the probability of sample task 51 generating output with current configuration.
																									if(((Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value60) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									
																									// Recorded the probability of reaching sample task 51 with the current configuration.
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value60);
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
										if(!true) {
											// Enumerating the possible outputs of Categorical 30.
											for(int index$sample31$49 = 0; index$sample31$49 < 3; index$sample31$49 += 1) {
												int distributionTempVariable$cat$51 = index$sample31$49;
												
												// Update the probability of sampling this value from the distribution value.
												double cv$probabilitySample31Value50 = (1.0 * distribution$sample31[index$sample31$49]);
												{
													int traceTempVariable$cat$52_1 = distributionTempVariable$cat$51;
													{
														if(!(traceTempVariable$cat$52_1 == 1)) {
															int traceTempVariable$result$54_1 = var43;
															
															// Processing sample task 51 of consumer random variable null.
															{
																{
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																			// the output of If task 47.
																			{
																				if(!(traceTempVariable$cat$52_1 == 1)) {
																					int traceTempVariable$result$63_1 = var43;
																					{
																						int traceTempVariable$cat$64_1 = cv$currentValue;
																						{
																							{
																								{
																									// Constructing a random variable input for use later.
																									double var47 = (double)traceTempVariable$result$63_1;
																									
																									// Constructing a random variable input for use later.
																									double var46 = (double)traceTempVariable$cat$64_1;
																									
																									// Record the probability of sample task 51 generating output with current configuration.
																									if(((Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									
																									// Recorded the probability of reaching sample task 51 with the current configuration.
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value50);
																								}
																							}
																						}
																					}
																					{
																						int traceTempVariable$cat$65_1 = distributionTempVariable$cat$51;
																						{
																							{
																								{
																									// Constructing a random variable input for use later.
																									double var47 = (double)traceTempVariable$result$63_1;
																									
																									// Constructing a random variable input for use later.
																									double var46 = (double)traceTempVariable$cat$65_1;
																									
																									// Record the probability of sample task 51 generating output with current configuration.
																									if(((Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																									else {
																										// If the second value is -infinity.
																										if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																											cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																										else
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value50) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																									}
																									
																									// Recorded the probability of reaching sample task 51 with the current configuration.
																									cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value50);
																								}
																							}
																						}
																					}
																					if(!true) {
																						// Enumerating the possible outputs of Categorical 30.
																						for(int index$sample31$66 = 0; index$sample31$66 < 3; index$sample31$66 += 1) {
																							int distributionTempVariable$cat$68 = index$sample31$66;
																							
																							// Update the probability of sampling this value from the distribution value.
																							double cv$probabilitySample31Value67 = (cv$probabilitySample31Value50 * distribution$sample31[index$sample31$66]);
																							{
																								int traceTempVariable$cat$69_1 = distributionTempVariable$cat$68;
																								{
																									{
																										{
																											// Constructing a random variable input for use later.
																											double var47 = (double)traceTempVariable$result$63_1;
																											
																											// Constructing a random variable input for use later.
																											double var46 = (double)traceTempVariable$cat$69_1;
																											
																											// Record the probability of sample task 51 generating output with current configuration.
																											if(((Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																											else {
																												// If the second value is -infinity.
																												if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																													cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																												else
																													cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value67) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																											}
																											
																											// Recorded the probability of reaching sample task 51 with the current configuration.
																											cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value67);
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
									{
										{
											if(!(cv$currentValue == 1)) {
												// Processing sample task 45 of consumer random variable null.
												{
													{
														// Set an accumulator to sum the probabilities for each possible configuration of
														// inputs.
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														
														// Set an accumulator to record the consumer distributions not seen. Initially set
														// to 1 as seen values will be deducted from this value.
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																// Enumerating the possible arguments for the variable Binomial 42 which is consuming
																// the output of If task 47.
																{
																	int traceTempVariable$cat$77_1 = cv$currentValue;
																	{
																		double traceTempVariable$var40$82_1 = 0.2;
																		if((0 == traceTempVariable$cat$77_1)) {
																			{
																				// Constructing a random variable input for use later.
																				double var40 = bias[cv$currentValue];
																				
																				// Record the probability of sample task 45 generating output with current configuration.
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																				}
																				
																				// Recorded the probability of reaching sample task 45 with the current configuration.
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
																if(!true) {
																	// Enumerating the possible outputs of Categorical 30.
																	for(int index$sample31$78 = 0; index$sample31$78 < 3; index$sample31$78 += 1) {
																		int distributionTempVariable$cat$80 = index$sample31$78;
																		
																		// Update the probability of sampling this value from the distribution value.
																		double cv$probabilitySample31Value79 = (1.0 * distribution$sample31[index$sample31$78]);
																		{
																			int traceTempVariable$cat$81_1 = distributionTempVariable$cat$80;
																			{
																				double traceTempVariable$var40$83_1 = 0.2;
																				if((0 == traceTempVariable$cat$81_1)) {
																					{
																						// Constructing a random variable input for use later.
																						double var40 = bias[cv$currentValue];
																						
																						// Record the probability of sample task 45 generating output with current configuration.
																						if(((Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							// If the second value is -infinity.
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(cv$probabilitySample31Value79) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																						}
																						
																						// Recorded the probability of reaching sample task 45 with the current configuration.
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value79);
																					}
																				}
																			}
																		}
																	}
																}
																
																// Enumerating the possible arguments for the variable Binomial 42 which is consuming
																// the output of If task 47.
																{
																	int traceTempVariable$cat$84_1 = cv$currentValue;
																	{
																		double traceTempVariable$var40$89_1 = 0.3;
																		if((1 == traceTempVariable$cat$84_1)) {
																			{
																				// Constructing a random variable input for use later.
																				double var40 = bias[cv$currentValue];
																				
																				// Record the probability of sample task 45 generating output with current configuration.
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																				}
																				
																				// Recorded the probability of reaching sample task 45 with the current configuration.
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
																if(!true) {
																	// Enumerating the possible outputs of Categorical 30.
																	for(int index$sample31$85 = 0; index$sample31$85 < 3; index$sample31$85 += 1) {
																		int distributionTempVariable$cat$87 = index$sample31$85;
																		
																		// Update the probability of sampling this value from the distribution value.
																		double cv$probabilitySample31Value86 = (1.0 * distribution$sample31[index$sample31$85]);
																		{
																			int traceTempVariable$cat$88_1 = distributionTempVariable$cat$87;
																			{
																				double traceTempVariable$var40$90_1 = 0.3;
																				if((1 == traceTempVariable$cat$88_1)) {
																					{
																						// Constructing a random variable input for use later.
																						double var40 = bias[cv$currentValue];
																						
																						// Record the probability of sample task 45 generating output with current configuration.
																						if(((Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							// If the second value is -infinity.
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(cv$probabilitySample31Value86) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																						}
																						
																						// Recorded the probability of reaching sample task 45 with the current configuration.
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value86);
																					}
																				}
																			}
																		}
																	}
																}
																
																// Enumerating the possible arguments for the variable Binomial 42 which is consuming
																// the output of If task 47.
																{
																	int traceTempVariable$cat$91_1 = cv$currentValue;
																	{
																		double traceTempVariable$var40$96_1 = 0.5;
																		if((2 == traceTempVariable$cat$91_1)) {
																			{
																				// Constructing a random variable input for use later.
																				double var40 = bias[cv$currentValue];
																				
																				// Record the probability of sample task 45 generating output with current configuration.
																				if(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																				}
																				
																				// Recorded the probability of reaching sample task 45 with the current configuration.
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
																if(!true) {
																	// Enumerating the possible outputs of Categorical 30.
																	for(int index$sample31$92 = 0; index$sample31$92 < 3; index$sample31$92 += 1) {
																		int distributionTempVariable$cat$94 = index$sample31$92;
																		
																		// Update the probability of sampling this value from the distribution value.
																		double cv$probabilitySample31Value93 = (1.0 * distribution$sample31[index$sample31$92]);
																		{
																			int traceTempVariable$cat$95_1 = distributionTempVariable$cat$94;
																			{
																				double traceTempVariable$var40$97_1 = 0.5;
																				if((2 == traceTempVariable$cat$95_1)) {
																					{
																						// Constructing a random variable input for use later.
																						double var40 = bias[cv$currentValue];
																						
																						// Record the probability of sample task 45 generating output with current configuration.
																						if(((Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							// If the second value is -infinity.
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)))) + 1)) + (Math.log(cv$probabilitySample31Value93) + DistributionSampling.logProbabilityBinomial(var43, var40, 10)));
																						}
																						
																						// Recorded the probability of reaching sample task 45 with the current configuration.
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value93);
																					}
																				}
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
				
				// Save the calculated index value into the array of index value probabilities
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			
			// Set the calculated probabilities to be the distribution values, and normalize
			// 
			// Local copy of the probability array
			double[] cv$localProbability = distribution$sample31;
			
			// The sum of all the probabilities in log space
			double cv$logSum = 0.0;
			
			// Sum all the values
			{
				// Initialise the max to the first element.
				double cv$lseMax = cv$stateProbabilityLocal[0];
				
				// Find max value.
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
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
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					
					// Increment the value of the target, moving the value back into log space.
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	// Method to perform the inference steps to calculate new values for the samples generated
	// by sample task 45 drawn from Binomial 42. Inference was performed using variable
	// marginalization.
	private final void sample45() {
		if(true) {
			// Calculate the number of states to evaluate.
			int cv$numStates = 0;
			
			// Exploring all the possible state counts for random variable 42.
			// 
			// Enumerating the possible arguments for Binomial 42.
			if(fixedFlag$sample31) {
				{
					{
						double traceTempVariable$var40$6_1 = 0.2;
						if((0 == cat))
							// variable marginalization
							cv$numStates = Math.max(cv$numStates, (10 + 1));
					}
				}
			} else {
				if(true) {
					// Enumerating the possible outputs of Categorical 30.
					for(int index$sample31$2 = 0; index$sample31$2 < 3; index$sample31$2 += 1) {
						int distributionTempVariable$cat$4 = index$sample31$2;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample31Value3 = (1.0 * distribution$sample31[index$sample31$2]);
						{
							{
								double traceTempVariable$var40$7_1 = 0.2;
								if((0 == distributionTempVariable$cat$4))
									// variable marginalization
									cv$numStates = Math.max(cv$numStates, (10 + 1));
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Binomial 42.
			if(fixedFlag$sample31) {
				{
					{
						double traceTempVariable$var40$13_1 = 0.3;
						if((1 == cat))
							// variable marginalization
							cv$numStates = Math.max(cv$numStates, (10 + 1));
					}
				}
			} else {
				if(true) {
					// Enumerating the possible outputs of Categorical 30.
					for(int index$sample31$9 = 0; index$sample31$9 < 3; index$sample31$9 += 1) {
						int distributionTempVariable$cat$11 = index$sample31$9;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample31Value10 = (1.0 * distribution$sample31[index$sample31$9]);
						{
							{
								double traceTempVariable$var40$14_1 = 0.3;
								if((1 == distributionTempVariable$cat$11))
									// variable marginalization
									cv$numStates = Math.max(cv$numStates, (10 + 1));
							}
						}
					}
				}
			}
			
			// Enumerating the possible arguments for Binomial 42.
			if(fixedFlag$sample31) {
				{
					{
						double traceTempVariable$var40$20_1 = 0.5;
						if((2 == cat))
							// variable marginalization
							cv$numStates = Math.max(cv$numStates, (10 + 1));
					}
				}
			} else {
				if(true) {
					// Enumerating the possible outputs of Categorical 30.
					for(int index$sample31$16 = 0; index$sample31$16 < 3; index$sample31$16 += 1) {
						int distributionTempVariable$cat$18 = index$sample31$16;
						
						// Update the probability of sampling this value from the distribution value.
						double cv$probabilitySample31Value17 = (1.0 * distribution$sample31[index$sample31$16]);
						{
							{
								double traceTempVariable$var40$21_1 = 0.5;
								if((2 == distributionTempVariable$cat$18))
									// variable marginalization
									cv$numStates = Math.max(cv$numStates, (10 + 1));
							}
						}
					}
				}
			}
			
			// Get a local reference to the scratch space.
			double[] cv$stateProbabilityLocal = cv$var43$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				// Exploring all the possible distribution values for random variable 42 creating
				// sample task 45.
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
				var43 = cv$currentValue;
				
				// Guards to ensure that result is only updated when there is a valid path.
				{
					{
						if(!(cat == 1)) {
							{
								result = cv$currentValue;
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Binomial 42.
				if(fixedFlag$sample31) {
					{
						{
							double traceTempVariable$var40$28_1 = 0.2;
							if((0 == cat)) {
								// Record the reached probability density.
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								
								// An accumulator to allow the value for each distribution to be constructed before
								// it is added to the index probabilities.
								double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$28_1, 10));
								
								// Processing random variable 48.
								{
									{
										{
											{
												if(!(cat == 1)) {
													int traceTempVariable$result$45_1 = cv$currentValue;
													
													// Processing sample task 51 of consumer random variable null.
													{
														{
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																// the output of Sample task 45.
																{
																	{
																		{
																			{
																				// Constructing a random variable input for use later.
																				double var47 = (double)traceTempVariable$result$45_1;
																				
																				// Constructing a random variable input for use later.
																				double var46 = (double)cat;
																				
																				// Record the probability of sample task 51 generating output with current configuration.
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																				}
																				
																				// Recorded the probability of reaching sample task 51 with the current configuration.
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
						// Enumerating the possible outputs of Categorical 30.
						for(int index$sample31$24 = 0; index$sample31$24 < 3; index$sample31$24 += 1) {
							int distributionTempVariable$cat$26 = index$sample31$24;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample31Value25 = (1.0 * distribution$sample31[index$sample31$24]);
							{
								{
									double traceTempVariable$var40$29_1 = 0.2;
									if((0 == distributionTempVariable$cat$26)) {
										// Record the reached probability density.
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample31Value25);
										
										// An accumulator to allow the value for each distribution to be constructed before
										// it is added to the index probabilities.
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample31Value25) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$29_1, 10));
										
										// Processing random variable 48.
										{
											{
												{
													{
														if(!(distributionTempVariable$cat$26 == 1)) {
															int traceTempVariable$result$51_1 = cv$currentValue;
															
															// Processing sample task 51 of consumer random variable null.
															{
																{
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																		// the output of Sample task 45.
																		{
																			int traceTempVariable$cat$81_1 = distributionTempVariable$cat$26;
																			{
																				{
																					{
																						// Constructing a random variable input for use later.
																						double var47 = (double)traceTempVariable$result$51_1;
																						
																						// Constructing a random variable input for use later.
																						double var46 = (double)traceTempVariable$cat$81_1;
																						
																						// Record the probability of sample task 51 generating output with current configuration.
																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							// If the second value is -infinity.
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																						}
																						
																						// Recorded the probability of reaching sample task 51 with the current configuration.
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
																				}
																			}
																		}
																		if(!true) {
																			// Enumerating the possible outputs of Categorical 30.
																			for(int index$sample31$82 = 0; index$sample31$82 < 3; index$sample31$82 += 1) {
																				int distributionTempVariable$cat$84 = index$sample31$82;
																				
																				// Update the probability of sampling this value from the distribution value.
																				double cv$probabilitySample31Value83 = (1.0 * distribution$sample31[index$sample31$82]);
																				{
																					int traceTempVariable$cat$85_1 = distributionTempVariable$cat$84;
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var47 = (double)traceTempVariable$result$51_1;
																								
																								// Constructing a random variable input for use later.
																								double var46 = (double)traceTempVariable$cat$85_1;
																								
																								// Record the probability of sample task 51 generating output with current configuration.
																								if(((Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value83) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								
																								// Recorded the probability of reaching sample task 51 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value83);
																							}
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
													}
												}
												if(!true) {
													// Enumerating the possible outputs of Categorical 30.
													for(int index$sample31$47 = 0; index$sample31$47 < 3; index$sample31$47 += 1) {
														int distributionTempVariable$cat$49 = index$sample31$47;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample31Value48 = (1.0 * distribution$sample31[index$sample31$47]);
														{
															{
																if(!(distributionTempVariable$cat$49 == 1)) {
																	int traceTempVariable$result$52_1 = cv$currentValue;
																	
																	// Processing sample task 51 of consumer random variable null.
																	{
																		{
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																				// the output of Sample task 45.
																				{
																					int traceTempVariable$cat$86_1 = distributionTempVariable$cat$26;
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var47 = (double)traceTempVariable$result$52_1;
																								
																								// Constructing a random variable input for use later.
																								double var46 = (double)traceTempVariable$cat$86_1;
																								
																								// Record the probability of sample task 51 generating output with current configuration.
																								if(((Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								
																								// Recorded the probability of reaching sample task 51 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value48);
																							}
																						}
																					}
																				}
																				{
																					int traceTempVariable$cat$87_1 = distributionTempVariable$cat$49;
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var47 = (double)traceTempVariable$result$52_1;
																								
																								// Constructing a random variable input for use later.
																								double var46 = (double)traceTempVariable$cat$87_1;
																								
																								// Record the probability of sample task 51 generating output with current configuration.
																								if(((Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value48) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								
																								// Recorded the probability of reaching sample task 51 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value48);
																							}
																						}
																					}
																				}
																				if(!true) {
																					// Enumerating the possible outputs of Categorical 30.
																					for(int index$sample31$88 = 0; index$sample31$88 < 3; index$sample31$88 += 1) {
																						int distributionTempVariable$cat$90 = index$sample31$88;
																						
																						// Update the probability of sampling this value from the distribution value.
																						double cv$probabilitySample31Value89 = (cv$probabilitySample31Value48 * distribution$sample31[index$sample31$88]);
																						{
																							int traceTempVariable$cat$91_1 = distributionTempVariable$cat$90;
																							{
																								{
																									{
																										// Constructing a random variable input for use later.
																										double var47 = (double)traceTempVariable$result$52_1;
																										
																										// Constructing a random variable input for use later.
																										double var46 = (double)traceTempVariable$cat$91_1;
																										
																										// Record the probability of sample task 51 generating output with current configuration.
																										if(((Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											// If the second value is -infinity.
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value89) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																										}
																										
																										// Recorded the probability of reaching sample task 51 with the current configuration.
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value89);
																									}
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
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Binomial 42.
				if(fixedFlag$sample31) {
					{
						{
							double traceTempVariable$var40$35_1 = 0.3;
							if((1 == cat)) {
								// Record the reached probability density.
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								
								// An accumulator to allow the value for each distribution to be constructed before
								// it is added to the index probabilities.
								double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$35_1, 10));
								
								// Processing random variable 48.
								{
									{
										{
											{
												if(!(cat == 1)) {
													int traceTempVariable$result$54_1 = cv$currentValue;
													
													// Processing sample task 51 of consumer random variable null.
													{
														{
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																// the output of Sample task 45.
																{
																	{
																		{
																			{
																				// Constructing a random variable input for use later.
																				double var47 = (double)traceTempVariable$result$54_1;
																				
																				// Constructing a random variable input for use later.
																				double var46 = (double)cat;
																				
																				// Record the probability of sample task 51 generating output with current configuration.
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																				}
																				
																				// Recorded the probability of reaching sample task 51 with the current configuration.
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
						// Enumerating the possible outputs of Categorical 30.
						for(int index$sample31$31 = 0; index$sample31$31 < 3; index$sample31$31 += 1) {
							int distributionTempVariable$cat$33 = index$sample31$31;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample31Value32 = (1.0 * distribution$sample31[index$sample31$31]);
							{
								{
									double traceTempVariable$var40$36_1 = 0.3;
									if((1 == distributionTempVariable$cat$33)) {
										// Record the reached probability density.
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample31Value32);
										
										// An accumulator to allow the value for each distribution to be constructed before
										// it is added to the index probabilities.
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample31Value32) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$36_1, 10));
										
										// Processing random variable 48.
										{
											{
												{
													{
														if(!(distributionTempVariable$cat$33 == 1)) {
															int traceTempVariable$result$60_1 = cv$currentValue;
															
															// Processing sample task 51 of consumer random variable null.
															{
																{
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																		// the output of Sample task 45.
																		{
																			int traceTempVariable$cat$93_1 = distributionTempVariable$cat$33;
																			{
																				{
																					{
																						// Constructing a random variable input for use later.
																						double var47 = (double)traceTempVariable$result$60_1;
																						
																						// Constructing a random variable input for use later.
																						double var46 = (double)traceTempVariable$cat$93_1;
																						
																						// Record the probability of sample task 51 generating output with current configuration.
																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							// If the second value is -infinity.
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																						}
																						
																						// Recorded the probability of reaching sample task 51 with the current configuration.
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
																				}
																			}
																		}
																		if(!true) {
																			// Enumerating the possible outputs of Categorical 30.
																			for(int index$sample31$94 = 0; index$sample31$94 < 3; index$sample31$94 += 1) {
																				int distributionTempVariable$cat$96 = index$sample31$94;
																				
																				// Update the probability of sampling this value from the distribution value.
																				double cv$probabilitySample31Value95 = (1.0 * distribution$sample31[index$sample31$94]);
																				{
																					int traceTempVariable$cat$97_1 = distributionTempVariable$cat$96;
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var47 = (double)traceTempVariable$result$60_1;
																								
																								// Constructing a random variable input for use later.
																								double var46 = (double)traceTempVariable$cat$97_1;
																								
																								// Record the probability of sample task 51 generating output with current configuration.
																								if(((Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value95) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								
																								// Recorded the probability of reaching sample task 51 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value95);
																							}
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
													}
												}
												if(!true) {
													// Enumerating the possible outputs of Categorical 30.
													for(int index$sample31$56 = 0; index$sample31$56 < 3; index$sample31$56 += 1) {
														int distributionTempVariable$cat$58 = index$sample31$56;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample31Value57 = (1.0 * distribution$sample31[index$sample31$56]);
														{
															{
																if(!(distributionTempVariable$cat$58 == 1)) {
																	int traceTempVariable$result$61_1 = cv$currentValue;
																	
																	// Processing sample task 51 of consumer random variable null.
																	{
																		{
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																				// the output of Sample task 45.
																				{
																					int traceTempVariable$cat$98_1 = distributionTempVariable$cat$33;
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var47 = (double)traceTempVariable$result$61_1;
																								
																								// Constructing a random variable input for use later.
																								double var46 = (double)traceTempVariable$cat$98_1;
																								
																								// Record the probability of sample task 51 generating output with current configuration.
																								if(((Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								
																								// Recorded the probability of reaching sample task 51 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value57);
																							}
																						}
																					}
																				}
																				{
																					int traceTempVariable$cat$99_1 = distributionTempVariable$cat$58;
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var47 = (double)traceTempVariable$result$61_1;
																								
																								// Constructing a random variable input for use later.
																								double var46 = (double)traceTempVariable$cat$99_1;
																								
																								// Record the probability of sample task 51 generating output with current configuration.
																								if(((Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value57) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								
																								// Recorded the probability of reaching sample task 51 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value57);
																							}
																						}
																					}
																				}
																				if(!true) {
																					// Enumerating the possible outputs of Categorical 30.
																					for(int index$sample31$100 = 0; index$sample31$100 < 3; index$sample31$100 += 1) {
																						int distributionTempVariable$cat$102 = index$sample31$100;
																						
																						// Update the probability of sampling this value from the distribution value.
																						double cv$probabilitySample31Value101 = (cv$probabilitySample31Value57 * distribution$sample31[index$sample31$100]);
																						{
																							int traceTempVariable$cat$103_1 = distributionTempVariable$cat$102;
																							{
																								{
																									{
																										// Constructing a random variable input for use later.
																										double var47 = (double)traceTempVariable$result$61_1;
																										
																										// Constructing a random variable input for use later.
																										double var46 = (double)traceTempVariable$cat$103_1;
																										
																										// Record the probability of sample task 51 generating output with current configuration.
																										if(((Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											// If the second value is -infinity.
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value101) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																										}
																										
																										// Recorded the probability of reaching sample task 51 with the current configuration.
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value101);
																									}
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
								}
							}
						}
					}
				}
				
				// Enumerating the possible arguments for Binomial 42.
				if(fixedFlag$sample31) {
					{
						{
							double traceTempVariable$var40$42_1 = 0.5;
							if((2 == cat)) {
								// Record the reached probability density.
								cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
								
								// An accumulator to allow the value for each distribution to be constructed before
								// it is added to the index probabilities.
								double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$42_1, 10));
								
								// Processing random variable 48.
								{
									{
										{
											{
												if(!(cat == 1)) {
													int traceTempVariable$result$63_1 = cv$currentValue;
													
													// Processing sample task 51 of consumer random variable null.
													{
														{
															// Set an accumulator to sum the probabilities for each possible configuration of
															// inputs.
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															
															// Set an accumulator to record the consumer distributions not seen. Initially set
															// to 1 as seen values will be deducted from this value.
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																// the output of Sample task 45.
																{
																	{
																		{
																			{
																				// Constructing a random variable input for use later.
																				double var47 = (double)traceTempVariable$result$63_1;
																				
																				// Constructing a random variable input for use later.
																				double var46 = (double)cat;
																				
																				// Record the probability of sample task 51 generating output with current configuration.
																				if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					// If the second value is -infinity.
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																				}
																				
																				// Recorded the probability of reaching sample task 51 with the current configuration.
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
						// Enumerating the possible outputs of Categorical 30.
						for(int index$sample31$38 = 0; index$sample31$38 < 3; index$sample31$38 += 1) {
							int distributionTempVariable$cat$40 = index$sample31$38;
							
							// Update the probability of sampling this value from the distribution value.
							double cv$probabilitySample31Value39 = (1.0 * distribution$sample31[index$sample31$38]);
							{
								{
									double traceTempVariable$var40$43_1 = 0.5;
									if((2 == distributionTempVariable$cat$40)) {
										// Record the reached probability density.
										cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample31Value39);
										
										// An accumulator to allow the value for each distribution to be constructed before
										// it is added to the index probabilities.
										double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample31Value39) + DistributionSampling.logProbabilityBinomial(cv$currentValue, traceTempVariable$var40$43_1, 10));
										
										// Processing random variable 48.
										{
											{
												{
													{
														if(!(distributionTempVariable$cat$40 == 1)) {
															int traceTempVariable$result$69_1 = cv$currentValue;
															
															// Processing sample task 51 of consumer random variable null.
															{
																{
																	// Set an accumulator to sum the probabilities for each possible configuration of
																	// inputs.
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	
																	// Set an accumulator to record the consumer distributions not seen. Initially set
																	// to 1 as seen values will be deducted from this value.
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																		// the output of Sample task 45.
																		{
																			int traceTempVariable$cat$105_1 = distributionTempVariable$cat$40;
																			{
																				{
																					{
																						// Constructing a random variable input for use later.
																						double var47 = (double)traceTempVariable$result$69_1;
																						
																						// Constructing a random variable input for use later.
																						double var46 = (double)traceTempVariable$cat$105_1;
																						
																						// Record the probability of sample task 51 generating output with current configuration.
																						if(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							// If the second value is -infinity.
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																						}
																						
																						// Recorded the probability of reaching sample task 51 with the current configuration.
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																					}
																				}
																			}
																		}
																		if(!true) {
																			// Enumerating the possible outputs of Categorical 30.
																			for(int index$sample31$106 = 0; index$sample31$106 < 3; index$sample31$106 += 1) {
																				int distributionTempVariable$cat$108 = index$sample31$106;
																				
																				// Update the probability of sampling this value from the distribution value.
																				double cv$probabilitySample31Value107 = (1.0 * distribution$sample31[index$sample31$106]);
																				{
																					int traceTempVariable$cat$109_1 = distributionTempVariable$cat$108;
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var47 = (double)traceTempVariable$result$69_1;
																								
																								// Constructing a random variable input for use later.
																								double var46 = (double)traceTempVariable$cat$109_1;
																								
																								// Record the probability of sample task 51 generating output with current configuration.
																								if(((Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value107) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								
																								// Recorded the probability of reaching sample task 51 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value107);
																							}
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
													}
												}
												if(!true) {
													// Enumerating the possible outputs of Categorical 30.
													for(int index$sample31$65 = 0; index$sample31$65 < 3; index$sample31$65 += 1) {
														int distributionTempVariable$cat$67 = index$sample31$65;
														
														// Update the probability of sampling this value from the distribution value.
														double cv$probabilitySample31Value66 = (1.0 * distribution$sample31[index$sample31$65]);
														{
															{
																if(!(distributionTempVariable$cat$67 == 1)) {
																	int traceTempVariable$result$70_1 = cv$currentValue;
																	
																	// Processing sample task 51 of consumer random variable null.
																	{
																		{
																			// Set an accumulator to sum the probabilities for each possible configuration of
																			// inputs.
																			double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																			
																			// Set an accumulator to record the consumer distributions not seen. Initially set
																			// to 1 as seen values will be deducted from this value.
																			double cv$consumerDistributionProbabilityAccumulator = 1.0;
																			{
																				// Enumerating the possible arguments for the variable Gaussian 48 which is consuming
																				// the output of Sample task 45.
																				{
																					int traceTempVariable$cat$110_1 = distributionTempVariable$cat$40;
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var47 = (double)traceTempVariable$result$70_1;
																								
																								// Constructing a random variable input for use later.
																								double var46 = (double)traceTempVariable$cat$110_1;
																								
																								// Record the probability of sample task 51 generating output with current configuration.
																								if(((Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								
																								// Recorded the probability of reaching sample task 51 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value66);
																							}
																						}
																					}
																				}
																				{
																					int traceTempVariable$cat$111_1 = distributionTempVariable$cat$67;
																					{
																						{
																							{
																								// Constructing a random variable input for use later.
																								double var47 = (double)traceTempVariable$result$70_1;
																								
																								// Constructing a random variable input for use later.
																								double var46 = (double)traceTempVariable$cat$111_1;
																								
																								// Record the probability of sample task 51 generating output with current configuration.
																								if(((Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																									cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																								else {
																									// If the second value is -infinity.
																									if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																										cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																									else
																										cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value66) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																								}
																								
																								// Recorded the probability of reaching sample task 51 with the current configuration.
																								cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value66);
																							}
																						}
																					}
																				}
																				if(!true) {
																					// Enumerating the possible outputs of Categorical 30.
																					for(int index$sample31$112 = 0; index$sample31$112 < 3; index$sample31$112 += 1) {
																						int distributionTempVariable$cat$114 = index$sample31$112;
																						
																						// Update the probability of sampling this value from the distribution value.
																						double cv$probabilitySample31Value113 = (cv$probabilitySample31Value66 * distribution$sample31[index$sample31$112]);
																						{
																							int traceTempVariable$cat$115_1 = distributionTempVariable$cat$114;
																							{
																								{
																									{
																										// Constructing a random variable input for use later.
																										double var47 = (double)traceTempVariable$result$70_1;
																										
																										// Constructing a random variable input for use later.
																										double var46 = (double)traceTempVariable$cat$115_1;
																										
																										// Record the probability of sample task 51 generating output with current configuration.
																										if(((Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) < cv$accumulatedConsumerProbabilities))
																											cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																										else {
																											// If the second value is -infinity.
																											if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																												cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46))));
																											else
																												cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))))) + 1)) + (Math.log(cv$probabilitySample31Value113) + (DistributionSampling.logProbabilityGaussian(((data - var47) / Math.sqrt(var46))) - (0.5 * Math.log(var46)))));
																										}
																										
																										// Recorded the probability of reaching sample task 51 with the current configuration.
																										cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample31Value113);
																									}
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
				for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
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
					for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
						cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
					
					// Increment the value of the target, moving the value back into log space.
					cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
				}
			}
			
			// If all the sum is zero, just share the probability evenly.
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				// Normalize log space values and move to normal space
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
			}
			
			// Set array values that are not computed for the input to negative infinity.
			for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
			
			// Write out the new value of the sample.
			var43 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
			
			// Guards to ensure that result is only updated when there is a valid path.
			{
				{
					if(!(cat == 1)) {
						{
							result = var43;
						}
					}
				}
			}
		}
	}

	// Method to allocate space temporary variables used by the inference methods. Allocating
	// here prevents repeated allocation and deallocation, and makes the code more amenable
	// to GPU execution.
	@Override
	public final void allocateScratch() {
		// Allocate scratch space.
		// Constructor for cv$var31$stateProbabilityGlobal
		{
			// Allocation of cv$var31$stateProbabilityGlobal for single threaded execution
			cv$var31$stateProbabilityGlobal = new double[3];
		}
		
		// Constructor for cv$var43$stateProbabilityGlobal
		{
			// Allocation of cv$var43$stateProbabilityGlobal for single threaded execution
			cv$var43$stateProbabilityGlobal = new double[(10 + 1)];
		}
	}

	// Method to allocate space for model inputs and outputs.
	@Override
	public final void allocator() {
		// Constructor for bias
		{
			bias = new double[3];
		}
		
		// Constructor for prob
		{
			prob = new double[3];
		}
		
		// Constructor for distribution$sample31
		{
			distribution$sample31 = new double[3];
		}
		
		// Allocate scratch space
		allocateScratch();
	}

	// Method to execute the model code conventionally.
	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if(!(fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else {
			if(!fixedFlag$sample31)
				result = 5;
		}
		data = ((Math.sqrt(cat) * DistributionSampling.sampleGaussian(RNG$)) + (double)result);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are calculated
	// and stored.
	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		// Create local copy of variable probabilities.
		double[] cv$distribution$sample31 = distribution$sample31;
		for(int index$var30 = 0; index$var30 < 3; index$var30 += 1) {
			// Probability for this value
			double cv$value = (((0.0 <= index$var30) && (index$var30 < 3))?prob[index$var30]:0.0);
			if(!fixedFlag$sample31)
				// Save the probability of each value
				cv$distribution$sample31[index$var30] = cv$value;
		}
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
		}
	}

	// Method to execute the model code conventionally with priming of fixed intermediate
	// variables.
	@Override
	public final void forwardGenerationPrime() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if(!(fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else
			result = 5;
		data = ((Math.sqrt(cat) * DistributionSampling.sampleGaussian(RNG$)) + (double)result);
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Distributions are collapsed to single values.
	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if(!(fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else {
			if(!fixedFlag$sample31)
				result = 5;
		}
	}

	// Method to execute the model code conventionally, excluding the elements that generate
	// observed values. Fixed intermediate variables are primed. Distributions are collapsed
	// to single values.
	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!fixedFlag$sample31)
			cat = DistributionSampling.sampleCategorical(RNG$, prob, 3);
		if(!(cat == 1)) {
			if(!fixedFlag$sample45)
				var43 = DistributionSampling.sampleBinomial(RNG$, bias[cat], 10);
			if(!(fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else
			result = 5;
	}

	// Method to execute one round of Gibbs sampling.
	@Override
	public final void gibbsRound() {
		// Infer the samples in chronological order.
		if(system$gibbsForward) {
			if(!fixedFlag$sample31)
				sample31();
			if(!(cat == 1)) {
				if(!fixedFlag$sample45)
					sample45();
			}
		}
		// Infer the samples in reverse chronological order.
		else {
			if(!(cat == 1)) {
				if(!fixedFlag$sample45)
					sample45();
			}
			if(!fixedFlag$sample31)
				sample31();
		}
		
		// Reverse the direction of execution for the next iteration
		system$gibbsForward = !system$gibbsForward;
	}

	// Method for initialising the model into a valid state before commencing inference
	// etc.
	@Override
	public final void initializeConstants() {
		bias[0] = 0.2;
		bias[1] = 0.3;
		bias[2] = 0.5;
		prob[0] = 0.2;
		prob[1] = 0.4;
		prob[2] = 0.4;
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
		if(!fixedProbFlag$sample31)
			logProbability$cat = Double.NaN;
		logProbability$var43 = 0.0;
		logProbability$result = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$sample45 = Double.NaN;
		if(!fixedProbFlag$sample51)
			logProbability$data = Double.NaN;
	}

	// Construct the evidence probabilities.
	@Override
	public final void logEvidenceProbabilities() {
		// Reset all the non-fixed probabilities ready to calculate the new values.
		initializeLogProbabilityFields();
		
		// Call each method in turn to generate the new probability values.
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		logProbabilityValue$sample51();
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
		logProbabilityDistribution$sample31();
		logProbabilityDistribution$sample45();
		logProbabilityDistribution$sample51();
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
		logProbabilityValue$sample31();
		logProbabilityValue$sample45();
		logProbabilityValue$sample51();
	}

	// Method to propagate observed values back into the model.
	@Override
	public final void propagateObservedValues() {
		data = observedData;
	}

	// A method to set array values that depend on the output of a sample task, but are
	// not directly set by the sample task. This method is called to propagate set values
	// through the model. Any non-fixed sample values may be sampled to random variables
	// as part of this process.
	@Override
	public final void setIntermediates() {
		if(!(cat == 1)) {
			if((fixedFlag$sample31 && fixedFlag$sample45))
				result = var43;
		} else
			result = 5;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + " \n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model DistributionTest7(double observedData ) {\n"
		     + "\n"
		     + "    double[] bias = {0.2, 0.3, 0.5};\n"
		     + "    double[] prob = {0.2, 0.4, 0.4};\n"
		     + "\n"
		     + "    int cat = categorical(prob).sampleDistribution();\n"
		     + "    int result;\n"
		     + "    if(cat != 1) {\n"
		     + "        result = binomial(bias[cat], 10).sample();\n"
		     + "    } else {\n"
		     + "        result = 5;\n"
		     + "    }\n"
		     + "    \n"
		     + "\n"
		     + "    double data = gaussian(result, (double) cat).sample();\n"
		     + "\n"
		     + "    data.observe(observedData);\n"
		     + "\n"
		     + "    }";
	}
}