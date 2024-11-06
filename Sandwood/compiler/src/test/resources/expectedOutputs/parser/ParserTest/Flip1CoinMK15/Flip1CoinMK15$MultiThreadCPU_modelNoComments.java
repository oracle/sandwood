package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class Flip1CoinMK15$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements Flip1CoinMK15$CoreInterface {
	private double b;
	private double bias;
	private double[] c;
	private boolean fixedFlag$sample12 = false;
	private boolean fixedFlag$sample40 = false;
	private boolean fixedProbFlag$sample12 = false;
	private boolean fixedProbFlag$sample40 = false;
	private boolean[] flips;
	private boolean[] flipsMeasured;
	private boolean guard1;
	private int length$flipsMeasured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$b;
	private double logProbability$bernoulli;
	private double logProbability$bias;
	private double logProbability$c;
	private double logProbability$flips;
	private double logProbability$var35;
	private double logProbability$var9;
	private int samples;
	private boolean setFlag$flips = false;
	private boolean system$gibbsForward = true;

	public Flip1CoinMK15$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double get$b() {
		return b;
	}

	@Override
	public final void set$b(double cv$value) {
		b = cv$value;
	}

	@Override
	public final double get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double cv$value) {
		bias = cv$value;
	}

	@Override
	public final boolean get$fixedFlag$sample12() {
		return fixedFlag$sample12;
	}

	@Override
	public final void set$fixedFlag$sample12(boolean cv$value) {
		fixedFlag$sample12 = cv$value;
		fixedProbFlag$sample12 = (fixedFlag$sample12 && fixedProbFlag$sample12);
		fixedProbFlag$sample40 = (fixedFlag$sample12 && fixedProbFlag$sample40);
	}

	@Override
	public final boolean get$fixedFlag$sample40() {
		return fixedFlag$sample40;
	}

	@Override
	public final void set$fixedFlag$sample40(boolean cv$value) {
		fixedFlag$sample40 = cv$value;
		fixedProbFlag$sample40 = (fixedFlag$sample40 && fixedProbFlag$sample40);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final void set$flips(boolean[] cv$value) {
		flips = cv$value;
		setFlag$flips = true;
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
	public final double get$logProbability$b() {
		return logProbability$b;
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

	private final void logProbabilityValue$sample12() {
		if(!fixedProbFlag$sample12) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				double cv$sampleValue = b;
				{
					{
						double var6 = 1.0;
						double var8 = 1.0;
						double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var6, var8));
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
			logProbability$var9 = cv$sampleAccumulator;
			logProbability$b = cv$sampleProbability;
			boolean cv$guard$c = false;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$c) {
					cv$guard$c = true;
					logProbability$c = (logProbability$c + cv$accumulator);
				}
			}
			{
				if(!cv$guard$c) {
					cv$guard$c = true;
					logProbability$c = (logProbability$c + cv$accumulator);
				}
			}
			{
				if(guard1) {
					double traceTempVariable$bias$4_1 = b;
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 0) && (0 < 2))) {
							{
								if(!guard1) {
									double reduceVar$var28$21 = 0.0;
									for(int cv$reduction489Index = 0; cv$reduction489Index < 2; cv$reduction489Index += 1) {
										double i$var25 = reduceVar$var28$21;
										double j = c[cv$reduction489Index];
										reduceVar$var28$21 = (i$var25 + j);
									}
									double traceTempVariable$bias$5_1 = reduceVar$var28$21;
									if(!cv$guard$bias) {
										cv$guard$bias = true;
										logProbability$bias = (logProbability$bias + cv$accumulator);
									}
								}
							}
						}
					}
				}
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 1) && (1 < 2))) {
							{
								if(!guard1) {
									double reduceVar$var28$22 = 0.0;
									for(int cv$reduction500Index = 0; cv$reduction500Index < 2; cv$reduction500Index += 1) {
										double i$var25 = reduceVar$var28$22;
										double j = c[cv$reduction500Index];
										reduceVar$var28$22 = (i$var25 + j);
									}
									double traceTempVariable$bias$6_1 = reduceVar$var28$22;
									if(!cv$guard$bias) {
										cv$guard$bias = true;
										logProbability$bias = (logProbability$bias + cv$accumulator);
									}
								}
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample12 = fixedFlag$sample12;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$b;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var9 = cv$rvAccumulator;
			boolean cv$guard$c = false;
			boolean cv$guard$bias = false;
			{
				if(!cv$guard$c) {
					cv$guard$c = true;
					logProbability$c = (logProbability$c + cv$accumulator);
				}
			}
			{
				if(!cv$guard$c) {
					cv$guard$c = true;
					logProbability$c = (logProbability$c + cv$accumulator);
				}
			}
			{
				if(guard1) {
					double traceTempVariable$bias$9_1 = b;
					if(!cv$guard$bias) {
						cv$guard$bias = true;
						logProbability$bias = (logProbability$bias + cv$accumulator);
					}
				}
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 0) && (0 < 2))) {
							{
								if(!guard1) {
									double reduceVar$var28$23 = 0.0;
									for(int cv$reduction518Index = 0; cv$reduction518Index < 2; cv$reduction518Index += 1) {
										double i$var25 = reduceVar$var28$23;
										double j = c[cv$reduction518Index];
										reduceVar$var28$23 = (i$var25 + j);
									}
									double traceTempVariable$bias$10_1 = reduceVar$var28$23;
									if(!cv$guard$bias) {
										cv$guard$bias = true;
										logProbability$bias = (logProbability$bias + cv$accumulator);
									}
								}
							}
						}
					}
				}
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 1) && (1 < 2))) {
							{
								if(!guard1) {
									double reduceVar$var28$24 = 0.0;
									for(int cv$reduction529Index = 0; cv$reduction529Index < 2; cv$reduction529Index += 1) {
										double i$var25 = reduceVar$var28$24;
										double j = c[cv$reduction529Index];
										reduceVar$var28$24 = (i$var25 + j);
									}
									double traceTempVariable$bias$11_1 = reduceVar$var28$24;
									if(!cv$guard$bias) {
										cv$guard$bias = true;
										logProbability$bias = (logProbability$bias + cv$accumulator);
									}
								}
							}
						}
					}
				}
			}
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample12)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample40() {
		if(!fixedProbFlag$sample40) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var34 = 0; var34 < samples; var34 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					boolean cv$sampleValue = flips[var34];
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
			logProbability$var35 = cv$sampleAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample40 = (fixedFlag$sample40 && fixedFlag$sample12);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var35;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bernoulli = cv$rvAccumulator;
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample12() {
		double cv$originalValue = b;
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					b = cv$proposedValue;
					{
						{
							c[0] = (cv$currentValue / 2);
						}
					}
					{
						{
							c[1] = (cv$currentValue / 2);
						}
					}
					{
						boolean guard$sample12if34 = false;
						if(guard1) {
							double traceTempVariable$bias$3_1 = cv$currentValue;
							if(!guard$sample12if34) {
								guard$sample12if34 = true;
								{
									bias = traceTempVariable$bias$3_1;
								}
							}
						}
						if(!guard1) {
							if(!guard1) {
								if(((0 <= 0) && (0 < 2))) {
									{
										if(!guard1) {
											double reduceVar$var28$15 = 0.0;
											for(int cv$reduction331Index = 0; cv$reduction331Index < 2; cv$reduction331Index += 1) {
												double i$var25 = reduceVar$var28$15;
												double j = c[cv$reduction331Index];
												reduceVar$var28$15 = (i$var25 + j);
											}
											double traceTempVariable$bias$4_1 = reduceVar$var28$15;
											if(!guard$sample12if34) {
												guard$sample12if34 = true;
												{
													bias = traceTempVariable$bias$4_1;
												}
											}
										}
									}
								}
							}
						}
						if(!guard1) {
							if(!guard1) {
								if(((0 <= 1) && (1 < 2))) {
									{
										if(!guard1) {
											double reduceVar$var28$16 = 0.0;
											for(int cv$reduction344Index = 0; cv$reduction344Index < 2; cv$reduction344Index += 1) {
												double i$var25 = reduceVar$var28$16;
												double j = c[cv$reduction344Index];
												reduceVar$var28$16 = (i$var25 + j);
											}
											double traceTempVariable$bias$5_1 = reduceVar$var28$16;
											if(!guard$sample12if34) {
												guard$sample12if34 = true;
												{
													bias = traceTempVariable$bias$5_1;
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
				cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
				double cv$temp$0$var6;
				{
					cv$temp$0$var6 = 1.0;
				}
				double cv$temp$1$var8;
				{
					cv$temp$1$var8 = 1.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$currentValue, cv$temp$0$var6, cv$temp$1$var8));
				{
					{
						boolean guard$sample12bernoulli35 = false;
						if(guard1) {
							double traceTempVariable$bias$6_1 = cv$currentValue;
							double traceTempVariable$b$6_2 = cv$currentValue;
							if(!guard$sample12bernoulli35) {
								guard$sample12bernoulli35 = true;
								{
									for(int var34 = 0; var34 < samples; var34 += 1) {
										double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
										double cv$consumerDistributionProbabilityAccumulator = 1.0;
										{
											{
												{
													{
														double cv$temp$2$bias;
														{
															cv$temp$2$bias = traceTempVariable$bias$6_1;
														}
														if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)) < cv$accumulatedConsumerProbabilities))
															cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
														else {
															if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias));
															else
																cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$2$bias)));
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
						if(!guard1) {
							double traceTempVariable$b$7_1 = cv$currentValue;
							double traceTempVariable$i$7_2 = (traceTempVariable$b$7_1 / 2);
							if(!guard1) {
								if(((0 <= 0) && (0 < 2))) {
									{
										if(((0 < c.length) && (0 < 2))) {
											double reduceVar$var28$17 = 0.0;
											for(int cv$reduction377Index = 0; cv$reduction377Index < 0; cv$reduction377Index += 1) {
												double i$var25 = reduceVar$var28$17;
												double j = c[cv$reduction377Index];
												reduceVar$var28$17 = (i$var25 + j);
											}
											for(int cv$reduction377Index = (0 + 1); cv$reduction377Index < 2; cv$reduction377Index += 1) {
												double i$var25 = reduceVar$var28$17;
												double j = c[cv$reduction377Index];
												reduceVar$var28$17 = (i$var25 + j);
											}
											double cv$reduced29 = reduceVar$var28$17;
											reduceVar$var28$17 = (traceTempVariable$i$7_2 + cv$reduced29);
											double traceTempVariable$var28$7_3 = reduceVar$var28$17;
											if(!guard1) {
												double traceTempVariable$bias$7_4 = traceTempVariable$var28$7_3;
												if(!guard$sample12bernoulli35) {
													guard$sample12bernoulli35 = true;
													{
														for(int var34 = 0; var34 < samples; var34 += 1) {
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$3$bias;
																			{
																				cv$temp$3$bias = traceTempVariable$bias$7_4;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$3$bias)));
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
						}
						if(!guard1) {
							double traceTempVariable$b$8_1 = cv$currentValue;
							double traceTempVariable$i$8_2 = (traceTempVariable$b$8_1 / 2);
							if(!guard1) {
								if(((0 <= 1) && (1 < 2))) {
									{
										if(((0 < c.length) && (0 < 2))) {
											double reduceVar$var28$18 = 0.0;
											for(int cv$reduction400Index = 0; cv$reduction400Index < 1; cv$reduction400Index += 1) {
												double i$var25 = reduceVar$var28$18;
												double j = c[cv$reduction400Index];
												reduceVar$var28$18 = (i$var25 + j);
											}
											for(int cv$reduction400Index = (1 + 1); cv$reduction400Index < 2; cv$reduction400Index += 1) {
												double i$var25 = reduceVar$var28$18;
												double j = c[cv$reduction400Index];
												reduceVar$var28$18 = (i$var25 + j);
											}
											double cv$reduced29 = reduceVar$var28$18;
											reduceVar$var28$18 = (traceTempVariable$i$8_2 + cv$reduced29);
											double traceTempVariable$var28$8_3 = reduceVar$var28$18;
											if(!guard1) {
												double traceTempVariable$bias$8_4 = traceTempVariable$var28$8_3;
												if(!guard$sample12bernoulli35) {
													guard$sample12bernoulli35 = true;
													{
														for(int var34 = 0; var34 < samples; var34 += 1) {
															double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
															double cv$consumerDistributionProbabilityAccumulator = 1.0;
															{
																{
																	{
																		{
																			double cv$temp$4$bias;
																			{
																				cv$temp$4$bias = traceTempVariable$bias$8_4;
																			}
																			if(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias)))) + 1)) + (Math.log(1.0) + DistributionSampling.logProbabilityBernoulli(flips[var34], cv$temp$4$bias)));
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
			b = cv$originalValue;
			{
				{
					c[0] = (b / 2);
				}
			}
			{
				{
					c[1] = (b / 2);
				}
			}
			{
				boolean guard$sample12if34 = false;
				if(guard1) {
					double traceTempVariable$bias$17_1 = b;
					if(!guard$sample12if34) {
						guard$sample12if34 = true;
						{
							bias = traceTempVariable$bias$17_1;
						}
					}
				}
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 0) && (0 < 2))) {
							{
								if(!guard1) {
									double reduceVar$var28$19 = 0.0;
									for(int cv$reduction449Index = 0; cv$reduction449Index < 2; cv$reduction449Index += 1) {
										double i$var25 = reduceVar$var28$19;
										double j = c[cv$reduction449Index];
										reduceVar$var28$19 = (i$var25 + j);
									}
									double traceTempVariable$bias$18_1 = reduceVar$var28$19;
									if(!guard$sample12if34) {
										guard$sample12if34 = true;
										{
											bias = traceTempVariable$bias$18_1;
										}
									}
								}
							}
						}
					}
				}
				if(!guard1) {
					if(!guard1) {
						if(((0 <= 1) && (1 < 2))) {
							{
								if(!guard1) {
									double reduceVar$var28$20 = 0.0;
									for(int cv$reduction462Index = 0; cv$reduction462Index < 2; cv$reduction462Index += 1) {
										double i$var25 = reduceVar$var28$20;
										double j = c[cv$reduction462Index];
										reduceVar$var28$20 = (i$var25 + j);
									}
									double traceTempVariable$bias$19_1 = reduceVar$var28$20;
									if(!guard$sample12if34) {
										guard$sample12if34 = true;
										{
											bias = traceTempVariable$bias$19_1;
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

	@Override
	public final void allocateScratch() {}

	@Override
	public final void allocator() {
		{
			if(!guard1)
				c = new double[2];
		}
		if(!setFlag$flips) {
			{
				flips = new boolean[length$flipsMeasured];
			}
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!fixedFlag$sample12)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!guard1) {
			if(!fixedFlag$sample12)
				c[0] = (b / 2);
			if(!fixedFlag$sample12)
				c[1] = (b / 2);
			double reduceVar$var28$25 = 0.0;
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				double i$var25 = reduceVar$var28$25;
				double j = c[cv$reduction29Index];
				if(!fixedFlag$sample12)
					reduceVar$var28$25 = (i$var25 + j);
			}
			if(!fixedFlag$sample12)
				bias = reduceVar$var28$25;
		}
		if(!fixedFlag$sample12)
			bias = b;
		parallelFor(RNG$, 0, samples, 1,
			(int forStart$var34, int forEnd$var34, int threadID$var34, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var34 = forStart$var34; var34 < forEnd$var34; var34 += 1) {
						if(!fixedFlag$sample40)
							flips[var34] = DistributionSampling.sampleBernoulli(RNG$1, bias);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		if(!fixedFlag$sample12)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!guard1) {
			if(!fixedFlag$sample12)
				c[0] = (b / 2);
			if(!fixedFlag$sample12)
				c[1] = (b / 2);
			double reduceVar$var28$27 = 0.0;
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				double i$var25 = reduceVar$var28$27;
				double j = c[cv$reduction29Index];
				if(!fixedFlag$sample12)
					reduceVar$var28$27 = (i$var25 + j);
			}
			if(!fixedFlag$sample12)
				bias = reduceVar$var28$27;
		}
		if(!fixedFlag$sample12)
			bias = b;
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!fixedFlag$sample12)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!guard1) {
			if(!fixedFlag$sample12)
				c[0] = (b / 2);
			if(!fixedFlag$sample12)
				c[1] = (b / 2);
			double reduceVar$var28$26 = 0.0;
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				double i$var25 = reduceVar$var28$26;
				double j = c[cv$reduction29Index];
				if(!fixedFlag$sample12)
					reduceVar$var28$26 = (i$var25 + j);
			}
			if(!fixedFlag$sample12)
				bias = reduceVar$var28$26;
		}
		if(!fixedFlag$sample12)
			bias = b;
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			if(!fixedFlag$sample12)
				sample12();
		} else {
			if(!fixedFlag$sample12)
				sample12();
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
		logProbability$var9 = 0.0;
		logProbability$c = 0.0;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample12)
			logProbability$b = 0.0;
		logProbability$bernoulli = 0.0;
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample40)
			logProbability$var35 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample12)
			logProbabilityValue$sample12();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample12();
		logProbabilityValue$sample40();
	}

	@Override
	public final void logProbabilityGeneration() {
		if(!fixedFlag$sample12)
			b = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		if(!guard1) {
			if(!fixedFlag$sample12)
				c[0] = (b / 2);
			if(!fixedFlag$sample12)
				c[1] = (b / 2);
			double reduceVar$var28$28 = 0.0;
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				double i$var25 = reduceVar$var28$28;
				double j = c[cv$reduction29Index];
				if(!fixedFlag$sample12)
					reduceVar$var28$28 = (i$var25 + j);
			}
			if(!fixedFlag$sample12)
				bias = reduceVar$var28$28;
		}
		if(!fixedFlag$sample12)
			bias = b;
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		for(int i$var39 = (samples - ((((samples - 1) - 0) % 1) + 1)); i$var39 >= ((0 - 1) + 1); i$var39 -= 1)
			flips[i$var39] = flipsMeasured[i$var39];
	}

	@Override
	public final void setIntermediates() {
		if(!guard1) {
			if(true)
				c[0] = (b / 2);
			if(true)
				c[1] = (b / 2);
			double reduceVar$var28$29 = 0.0;
			for(int cv$reduction29Index = 0; cv$reduction29Index < 2; cv$reduction29Index += 1) {
				double i$var25 = reduceVar$var28$29;
				double j = c[cv$reduction29Index];
				reduceVar$var28$29 = (i$var25 + j);
			}
			bias = reduceVar$var28$29;
		}
		if(true)
			bias = b;
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model Flip1CoinMK15(boolean[] flipsMeasured, boolean guard1) {\n    int samples = flipsMeasured.length;\n        \n    double b = beta(1.0, 1).sample();\n    double bias;\n    if(guard1)\n      bias = b;\n    else {\n      double[] c = new double[2];\n      c[0] = b/2;\n      c[1] = b/2;\n      bias = reduce(c, 0, (i,j) -> {\n            return i + j;\n        });\n    }\n        \n    Bernoulli bernoulli = bernoulli(bias);\n    boolean[] flips = bernoulli.sample(samples);\n\n    for(int i:[0..samples))\n        flips[i].observe(flipsMeasured[i]);\n}\n";
	}
}