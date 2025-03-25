package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Conditional4$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Conditional4$CoreInterface {
	private double[] bias;
	private double[] cv$var4$stateProbabilityGlobal;
	private boolean fixedFlag$sample21 = false;
	private boolean fixedFlag$sample4 = false;
	private boolean fixedProbFlag$sample21 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample4 = false;
	private boolean guard;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$guard;
	private double logProbability$sample21;
	private double logProbability$value;
	private double logProbability$var18;
	private double logProbability$var24;
	private double observedValue;
	private boolean system$gibbsForward = true;
	private double value;

	public Conditional4$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value) {
		bias = cv$value;
		fixedProbFlag$sample21 = false;
		fixedProbFlag$sample27 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample21() {
		return fixedFlag$sample21;
	}

	@Override
	public final void set$fixedFlag$sample21(boolean cv$value) {
		fixedFlag$sample21 = cv$value;
		fixedProbFlag$sample21 = (fixedFlag$sample21 && fixedProbFlag$sample21);
		fixedProbFlag$sample27 = (fixedFlag$sample21 && fixedProbFlag$sample27);
	}

	@Override
	public final boolean get$fixedFlag$sample4() {
		return fixedFlag$sample4;
	}

	@Override
	public final void set$fixedFlag$sample4(boolean cv$value) {
		fixedFlag$sample4 = cv$value;
		fixedProbFlag$sample4 = (fixedFlag$sample4 && fixedProbFlag$sample4);
		fixedProbFlag$sample27 = (fixedFlag$sample4 && fixedProbFlag$sample27);
	}

	@Override
	public final boolean get$guard() {
		return guard;
	}

	@Override
	public final void set$guard(boolean cv$value) {
		guard = cv$value;
		fixedProbFlag$sample4 = false;
		fixedProbFlag$sample27 = false;
	}

	@Override
	public final double get$logProbability$$evidence() {
		return logProbability$$evidence;
	}

	@Override
	public final double getCurrentLogProbability() {
		return logProbability$$model;
	}

	@Override
	public final double get$logProbability$bernoulli() {
		return logProbability$bernoulli;
	}

	@Override
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$guard() {
		return logProbability$guard;
	}

	@Override
	public final double get$logProbability$value() {
		return logProbability$value;
	}

	@Override
	public final double get$observedValue() {
		return observedValue;
	}

	@Override
	public final void set$observedValue(double cv$value) {
		observedValue = cv$value;
	}

	@Override
	public final double get$value() {
		return value;
	}

	private final void logProbabilityValue$sample21() {
		if(!fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			if(!guard) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					if(!guard) {
						double cv$sampleValue = bias[0];
						{
							{
								double var16 = 0.0;
								double var17 = 0.5;
								double cv$weightedProbability = (Math.log(1.0) + (((var16 <= cv$sampleValue) && (cv$sampleValue <= var17))?(-Math.log((var17 - var16))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + 1.0);
							}
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$var18 = cv$sampleAccumulator;
				logProbability$sample21 = cv$sampleProbability;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample21 = fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			if(!guard) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample21;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var18 = cv$rvAccumulator;
			}
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample21)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = value;
				{
					{
						double var22 = bias[0];
						double var23 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var22, var23));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + 1.0);
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var24 = cv$sampleAccumulator;
			logProbability$value = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = (fixedFlag$sample4 && fixedFlag$sample21);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$value;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var24 = cv$rvAccumulator;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample4() {
		if(!fixedProbFlag$sample4) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				boolean cv$sampleValue = guard;
				{
					{
						double var2 = 0.5;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, var2));
						if((cv$weightedProbability < cv$distributionAccumulator))
							cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
						else {
							if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
								cv$distributionAccumulator = cv$weightedProbability;
							else
								cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
						}
						cv$probabilityReached = (cv$probabilityReached + 1.0);
					}
				}
			}
			if((cv$probabilityReached == 0.0))
				cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			else
				cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
			double cv$sampleProbability = cv$distributionAccumulator;
			cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$guard = cv$sampleProbability;
			boolean cv$guard$bias = false;
			{
				if(guard) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			{
				if(!guard) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample4 = fixedFlag$sample4;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$guard;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			boolean cv$guard$bias = false;
			{
				if(guard) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			{
				if(!guard) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample4)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample21() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = bias[0];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var19 = cv$proposedValue;
					{
						if(!guard) {
							{
								bias[0] = cv$currentValue;
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var16;
				{
					cv$temp$0$var16 = 0.0;
				}
				double cv$temp$1$var17;
				{
					cv$temp$1$var17 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (((cv$temp$0$var16 <= cv$currentValue) && (cv$currentValue <= cv$temp$1$var17))?(-Math.log((cv$temp$1$var17 - cv$temp$0$var16))):Double.NEGATIVE_INFINITY));
				{
					{
						if(!guard) {
							double traceTempVariable$var22$2_1 = cv$currentValue;
							if((0 == 0)) {
								{
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									{
										{
											{
												{
													double cv$temp$2$var22;
													{
														double var22 = traceTempVariable$var22$2_1;
														cv$temp$2$var22 = var22;
													}
													double cv$temp$3$var23;
													{
														cv$temp$3$var23 = 1.0;
													}
													if(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23)) < cv$accumulatedConsumerProbabilities))
														cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
													else {
														if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
															cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23));
														else
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$2$var22, cv$temp$3$var23)));
													}
													cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
												}
											}
										}
									}
									cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
									if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
										cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
									else {
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
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			if((cv$valuePos == 0))
				cv$originalProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			else
				cv$proposedProbability = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$ratio = (cv$proposedProbability - cv$originalProbability);
		if((((cv$proposedProbability - cv$originalProbability) <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(RNG$))))) || Double.isNaN(cv$ratio))) {
			double var19 = cv$originalValue;
			{
				if(!guard) {
					{
						bias[0] = var19;
					}
				}
			}
		}
	}

	private final void sample4() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double[] cv$stateProbabilityLocal = cv$var4$stateProbabilityGlobal;
		for(int cv$valuePos = 0; cv$valuePos < cv$numNumStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			boolean cv$currentValue;
			cv$currentValue = (cv$valuePos == 1);
			guard = cv$currentValue;
			{
				if(cv$currentValue) {
					{
						bias[0] = 0.5;
					}
				}
			}
			{
				if(!cv$currentValue) {
					{
						bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var2;
				{
					cv$temp$0$var2 = 0.5;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$currentValue, cv$temp$0$var2));
				{
					{
						if(!cv$currentValue) {
							{
								{
									if(!cv$currentValue) {
										if((0 == 0)) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	double cv$temp$1$var22;
																	{
																		double var22 = bias[0];
																		cv$temp$1$var22 = var22;
																	}
																	double cv$temp$2$var23;
																	{
																		cv$temp$2$var23 = 1.0;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$1$var22, cv$temp$2$var23)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
												if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
												else {
													if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
														cv$accumulatedProbabilities = (Math.log(cv$consumerDistributionProbabilityAccumulator) + cv$accumulatedProbabilities);
													else
														cv$accumulatedProbabilities = ((Math.log((Math.exp((cv$accumulatedConsumerProbabilities - Math.log(cv$consumerDistributionProbabilityAccumulator))) + 1)) + Math.log(cv$consumerDistributionProbabilityAccumulator)) + cv$accumulatedProbabilities);
												}
											}
										}
									}
								}
								{
									if(!cv$currentValue) {
										{
											if(!cv$currentValue) {
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																double cv$temp$3$var16;
																{
																	cv$temp$3$var16 = 0.0;
																}
																double cv$temp$4$var17;
																{
																	cv$temp$4$var17 = 0.5;
																}
																if(((Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] <= cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] <= cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] <= cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] <= cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((cv$temp$3$var16 <= bias[0]) && (bias[0] <= cv$temp$4$var17))?(-Math.log((cv$temp$4$var17 - cv$temp$3$var16))):Double.NEGATIVE_INFINITY)));
																}
																cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
															}
														}
													}
												}
												cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
												if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
												else {
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
						if(cv$currentValue) {
							{
								{
									if(cv$currentValue) {
										double traceTempVariable$var22$10_1 = 0.5;
										if((0 == 0)) {
											{
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	double cv$temp$5$var22;
																	{
																		double var22 = bias[0];
																		cv$temp$5$var22 = var22;
																	}
																	double cv$temp$6$var23;
																	{
																		cv$temp$6$var23 = 1.0;
																	}
																	if(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBeta(value, cv$temp$5$var22, cv$temp$6$var23)));
																	}
																	cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																}
															}
														}
													}
												}
												cv$consumerDistributionProbabilityAccumulator = Math.max(cv$consumerDistributionProbabilityAccumulator, 0.0);
												if((Math.log(cv$consumerDistributionProbabilityAccumulator) < cv$accumulatedConsumerProbabilities))
													cv$accumulatedProbabilities = ((Math.log((Math.exp((Math.log(cv$consumerDistributionProbabilityAccumulator) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities) + cv$accumulatedProbabilities);
												else {
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
				if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
					cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
				else {
					if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					else
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
				}
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
		}
		double cv$logSum = 0.0;
		{
			double cv$lseMax = cv$stateProbabilityLocal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numNumStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
			}
		}
		if((cv$logSum == Double.NEGATIVE_INFINITY)) {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numNumStates);
		} else {
			for(int cv$indexName = 0; cv$indexName < cv$numNumStates; cv$indexName += 1)
				cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
		}
		for(int cv$indexName = cv$numNumStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
			cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
		guard = (DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numNumStates) == 1);
		{
			if(guard) {
				{
					bias[0] = 0.5;
				}
			}
		}
		{
			if(!guard) {
				{
					bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {
		cv$var4$stateProbabilityGlobal = new double[2];
	}

	@Override
	public final void allocator() {
		if(!(fixedFlag$sample4 && fixedFlag$sample21)) {
			{
				bias = new double[1];
			}
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		value = DistributionSampling.sampleBeta(RNG$, bias[0], 1.0);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample4)
				sample4();
			if(!guard) {
				if(!fixedFlag$sample21)
					sample21();
			}
		} else {
			if(!guard) {
				if(!fixedFlag$sample21)
					sample21();
			}
			if(!fixedFlag$sample4)
				sample4();
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample4)
			logProbability$guard = 0.0;
		logProbability$var18 = 0.0;
		if(!fixedProbFlag$sample21)
			logProbability$sample21 = 0.0;
		logProbability$var24 = 0.0;
		if(!fixedProbFlag$sample27)
			logProbability$value = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample4)
			logProbabilityValue$sample4();
		if(fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample4();
		logProbabilityValue$sample21();
		logProbabilityValue$sample27();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample4)
			guard = DistributionSampling.sampleBernoulli(RNG$, 0.5);
		if(guard)
			bias[0] = 0.5;
		else {
			if(!fixedFlag$sample21)
				bias[0] = (0.0 + ((0.5 - 0.0) * DistributionSampling.sampleUniform(RNG$)));
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		value = observedValue;
	}

	@Override
	public final void setIntermediates() {
		if(guard)
			bias[0] = 0.5;
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2023, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Conditional4(double observedValue)  {\n"
		     + "        \n"
		     + "    //Construct a bernoulli\n"
		     + "    Bernoulli bernoulli = bernoulli(0.5);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    boolean guard = bernoulli.sample();\n"
		     + "    \n"
		     + "    double[] bias = new double[1];\n"
		     + "        \n"
		     + "    if(guard)\n"
		     + "        bias[0] = 0.5;\n"
		     + "    else\n"
		     + "        bias[0] = uniform(0.0, 0.5).sample();\n"
		     + "    \n"
		     + "    double value = beta(bias[0],1.0).sample();\n"
		     + "    \n"
		     + "    //Link the sampled value to the observed value\n"
		     + "    value.observe(observedValue);\n"
		     + "}";
	}
}