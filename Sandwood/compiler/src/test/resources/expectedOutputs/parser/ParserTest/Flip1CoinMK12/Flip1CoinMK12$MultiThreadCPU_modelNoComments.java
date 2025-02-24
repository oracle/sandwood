package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK12$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK12$CoreInterface {
	private double bias;
	private boolean fixedFlag$sample16 = false;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample35 = false;
	private boolean fixedFlag$sample52 = false;
	private boolean fixedProbFlag$sample16 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample35 = false;
	private boolean fixedProbFlag$sample52 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int guard2;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$sample16;
	private double logProbability$sample28;
	private double logProbability$sample35;
	private double logProbability$var13;
	private double logProbability$var14;
	private double logProbability$var23;
	private double logProbability$var26;
	private double logProbability$var30;
	private double logProbability$var33;
	private double logProbability$var48;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;
	private double var14;
	private double var26;
	private double var33;

	public Flip1CoinMK12$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final boolean get$fixedFlag$sample16() {
		return fixedFlag$sample16;
	}

	@Override
	public final void set$fixedFlag$sample16(boolean cv$value) {
		fixedFlag$sample16 = cv$value;
		fixedProbFlag$sample16 = (fixedFlag$sample16 && fixedProbFlag$sample16);
		fixedProbFlag$sample52 = (fixedFlag$sample16 && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value) {
		fixedFlag$sample28 = cv$value;
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample52 = (fixedFlag$sample28 && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample35() {
		return fixedFlag$sample35;
	}

	@Override
	public final void set$fixedFlag$sample35(boolean cv$value) {
		fixedFlag$sample35 = cv$value;
		fixedProbFlag$sample35 = (fixedFlag$sample35 && fixedProbFlag$sample35);
		fixedProbFlag$sample52 = (fixedFlag$sample35 && fixedProbFlag$sample52);
	}

	@Override
	public final boolean get$fixedFlag$sample52() {
		return fixedFlag$sample52;
	}

	@Override
	public final void set$fixedFlag$sample52(boolean cv$value) {
		fixedFlag$sample52 = cv$value;
		fixedProbFlag$sample52 = (fixedFlag$sample52 && fixedProbFlag$sample52);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
		fixedProbFlag$sample52 = false;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value) {
		flipsMeasured = cv$value;
	}

	@Override
	public final boolean get$guard1() {
		return guard1;
	}

	@Override
	public final void set$guard1(boolean cv$value) {
		guard1 = cv$value;
	}

	@Override
	public final int get$guard2() {
		return guard2;
	}

	@Override
	public final void set$guard2(int cv$value) {
		guard2 = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value) {
		length$flipsMeasured = cv$value;
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
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	private final void logProbabilityValue$sample16() {
		if(!fixedProbFlag$sample16) {
			double cv$accumulator = 0.0;
			if(guard1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					if(guard1) {
						double cv$sampleValue = var14;
						{
							{
								double var10 = 1.0;
								double var12 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var10, var12));
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
				logProbability$var13 = cv$sampleAccumulator;
				logProbability$sample16 = cv$sampleProbability;
			}
			boolean cv$guard$bias = false;
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			{
				if(guard1) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample16 = fixedFlag$sample16;
		} else {
			double cv$accumulator = 0.0;
			if(guard1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample16;
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var13 = cv$rvAccumulator;
			}
			boolean cv$guard$bias = false;
			logProbability$var14 = (logProbability$var14 + cv$accumulator);
			{
				if(guard1) {
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample16)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if((guard2 <= 2)) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if(!guard1) {
							if((guard2 <= 2)) {
								double cv$sampleValue = (var26 * 2);
								{
									{
										double var20 = 1.0;
										double var22 = 1.0;
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var20, var22));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var23 = cv$sampleAccumulator;
					logProbability$sample28 = cv$sampleProbability;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var26 = (logProbability$var26 + cv$accumulator);
			{
				if(!guard1) {
					if((guard2 <= 2)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if((guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample28;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var23 = cv$rvAccumulator;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var26 = (logProbability$var26 + cv$accumulator);
			{
				if(!guard1) {
					if((guard2 <= 2)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample35() {
		if(!fixedProbFlag$sample35) {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if(!(guard2 <= 2)) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					{
						if(!guard1) {
							if(!(guard2 <= 2)) {
								double cv$sampleValue = (var33 * 3);
								{
									{
										double var27 = 1.0;
										double var29 = 1.0;
										double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var27, var29));
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
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					double cv$sampleProbability = cv$distributionAccumulator;
					cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
					cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
					logProbability$var30 = cv$sampleAccumulator;
					logProbability$sample35 = cv$sampleProbability;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var33 = (logProbability$var33 + cv$accumulator);
			{
				if(!guard1) {
					if(!(guard2 <= 2)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample35 = fixedFlag$sample35;
		} else {
			double cv$accumulator = 0.0;
			if(!guard1) {
				if(!(guard2 <= 2)) {
					double cv$rvAccumulator = 0.0;
					double cv$sampleValue = logProbability$sample35;
					cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
					cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					logProbability$var30 = cv$rvAccumulator;
				}
			}
			boolean cv$guard$bias = false;
			logProbability$var33 = (logProbability$var33 + cv$accumulator);
			{
				if(!guard1) {
					if(!(guard2 <= 2)) {
						if(!guard1) {
							if(!cv$guard$bias) {
								cv$guard$bias = true;
								logProbability$bias = (logProbability$bias + cv$accumulator);
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample35)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!fixedProbFlag$sample52) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var47 = 0; var47 < samples; var47 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[var47];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(cv$sampleValue, bias));
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
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$bernoulli = cv$sampleAccumulator;
			logProbability$var48 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample52 = (((fixedFlag$sample52 && fixedFlag$sample16) && fixedFlag$sample28) && fixedFlag$sample35);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var48;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample16() {
		int cv$sum = 0;
		int cv$count = 0;
		{
			{
				{
					if(guard1) {
						{
							for(int var47 = 0; var47 < samples; var47 += 1) {
								cv$count = (cv$count + 1);
								if(flips[var47])
									cv$sum = (cv$sum + 1);
							}
						}
					}
				}
			}
		}
		var14 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
		{
			if(guard1) {
				{
					bias = var14;
					if(!guard1) {
						double var34;
						if((guard2 <= 2))
							var34 = var26;
						else
							var34 = var33;
						bias = var34;
					}
				}
			}
		}
	}

	private final void sample28() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = (var26 * 2);
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
					double var24 = cv$proposedValue;
					var26 = (cv$currentValue / 2);
					{
						if(!guard1) {
							if((guard2 <= 2)) {
								if(!guard1) {
									{
										if(guard1)
											bias = var14;
										double var34;
										var34 = var26;
										if(!(guard2 <= 2))
											var34 = var33;
										bias = var34;
									}
								}
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var20;
				{
					cv$temp$0$var20 = 1.0;
				}
				double cv$temp$1$var22;
				{
					cv$temp$1$var22 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var20, cv$temp$1$var22));
				{
					{
						if(!guard1) {
							if((guard2 <= 2)) {
								double traceTempVariable$var34$2_1 = var26;
								if(!guard1) {
									double traceTempVariable$bias$2_2 = traceTempVariable$var34$2_1;
									{
										for(int var47 = 0; var47 < samples; var47 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$bias;
															{
																cv$temp$2$bias = traceTempVariable$bias$2_2;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias)));
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
			double var24 = cv$originalValue;
			var26 = (var24 / 2);
			{
				if(!guard1) {
					if((guard2 <= 2)) {
						if(!guard1) {
							{
								if(guard1)
									bias = var14;
								double var34;
								var34 = var26;
								if(!(guard2 <= 2))
									var34 = var33;
								bias = var34;
							}
						}
					}
				}
			}
		}
	}

	private final void sample35() {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = (var33 * 3);
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
					double var31 = cv$proposedValue;
					var33 = (cv$currentValue / 3);
					{
						if(!guard1) {
							if(!(guard2 <= 2)) {
								if(!guard1) {
									{
										if(guard1)
											bias = var14;
										double var34;
										if((guard2 <= 2))
											var34 = var26;
										var34 = var33;
										bias = var34;
									}
								}
							}
						}
					}
				}
			}
			{
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var27;
				{
					cv$temp$0$var27 = 1.0;
				}
				double cv$temp$1$var29;
				{
					cv$temp$1$var29 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var27, cv$temp$1$var29));
				{
					{
						if(!guard1) {
							if(!(guard2 <= 2)) {
								double traceTempVariable$var34$2_1 = var33;
								if(!guard1) {
									double traceTempVariable$bias$2_2 = traceTempVariable$var34$2_1;
									{
										for(int var47 = 0; var47 < samples; var47 += 1) {
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															double cv$temp$2$bias;
															{
																cv$temp$2$bias = traceTempVariable$bias$2_2;
															}
															if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias)) < cv$accumulatedConsumerProbabilities))
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
															else {
																if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																	cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias));
																else
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var47], cv$temp$2$bias)));
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
			double var31 = cv$originalValue;
			var33 = (var31 / 3);
			{
				if(!guard1) {
					if(!(guard2 <= 2)) {
						if(!guard1) {
							{
								if(guard1)
									bias = var14;
								double var34;
								if((guard2 <= 2))
									var34 = var26;
								var34 = var33;
								bias = var34;
							}
						}
					}
				}
			}
		}
	}

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
	}

	@Override
	public final void forwardGeneration() {
		if(guard1) {
			if(!fixedFlag$sample16)
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample16)
				bias = var14;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28)
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample28)
					bias = var26;
			} else {
				if(!fixedFlag$sample35)
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample35)
					bias = var33;
			}
		}
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var47, int forEnd$var47, int threadID$var47, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var47 = forStart$var47; var47 < forEnd$var47; var47 += 1) {
						if(!fixedFlag$sample52)
							flips[var47] = DistributionSampling.sampleBernoulli(RNG$1, bias);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample16)
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample16)
				bias = var14;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28)
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample28)
					bias = var26;
			} else {
				if(!fixedFlag$sample35)
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample35)
					bias = var33;
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(guard1) {
			if(!fixedFlag$sample16)
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample16)
				bias = var14;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28)
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample28)
					bias = var26;
			} else {
				if(!fixedFlag$sample35)
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample35)
					bias = var33;
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(guard1) {
				if(!fixedFlag$sample16)
					sample16();
			} else {
				if((guard2 <= 2)) {
					if(!fixedFlag$sample28)
						sample28();
				} else {
					if(!fixedFlag$sample35)
						sample35();
				}
			}
		} else {
			if(guard1) {
				if(!fixedFlag$sample16)
					sample16();
			} else {
				if((guard2 <= 2)) {
					if(!fixedFlag$sample28)
						sample28();
				} else {
					if(!fixedFlag$sample35)
						sample35();
				}
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		samples = length$flipsMeasured;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$var13 = 0.0;
		logProbability$bias = 0.0;
		logProbability$var14 = 0.0;
		if(!fixedProbFlag$sample16)
			logProbability$sample16 = 0.0;
		logProbability$var23 = 0.0;
		logProbability$var26 = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$sample28 = 0.0;
		logProbability$var30 = 0.0;
		logProbability$var33 = 0.0;
		if(!fixedProbFlag$sample35)
			logProbability$sample35 = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample52)
			logProbability$var48 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample16)
			logProbabilityValue$sample16();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample35)
			logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
		logProbabilityValue$sample28();
		logProbabilityValue$sample35();
		logProbabilityValue$sample52();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(guard1) {
			if(!fixedFlag$sample16)
				var14 = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
			if(!fixedFlag$sample16)
				bias = var14;
		} else {
			if((guard2 <= 2)) {
				if(!fixedFlag$sample28)
					var26 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 2);
				if(!fixedFlag$sample28)
					bias = var26;
			} else {
				if(!fixedFlag$sample35)
					var33 = (DistributionSampling.sampleBeta(RNG$, 1.0, 1.0) / 3);
				if(!fixedFlag$sample35)
					bias = var33;
			}
		}
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		for(int i = (samples - ((((samples - 1) - 0) % 1) + 1)); i >= ((0 - 1) + 1); i -= 1)
			flips[i] = flipsMeasured[i];
	}

	@Override
	public final void setIntermediates() {
		if(true) {
			if(guard1)
				bias = var14;
			else {
				double var34;
				if((guard2 <= 2))
					var34 = var26;
				else
					var34 = var33;
				bias = var34;
			}
		}
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
		     + "public model Flip1CoinMK12(boolean[] flipsMeasured, boolean guard1, int guard2) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double bias;\n"
		     + "    if(guard1)\n"
		     + "      bias = beta(1.0, 1).sample();\n"
		     + "    else { \n"
		     + "        if(guard2 <= 2) {\n"
		     + "            bias = beta(1.0, 1).sample()/2;\n"
		     + "        } else\n"
		     + "            bias = beta(1.0, 1).sample()/3;\n"
		     + "    }\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        flips[i].observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}