package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoice$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoice$CoreInterface {
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample27 = false;
	private boolean fixedFlag$sample81 = false;
	private boolean fixedProbFlag$sample27 = false;
	private boolean fixedProbFlag$sample81 = false;
	private boolean[] guard$sample27put68$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample27;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[] logProbability$var25;
	private double logProbability$var67;
	private double logProbability$var79;
	private int noObs;
	private int noProducts;
	private double[] prob;
	private boolean setFlag$choices = false;
	private boolean setFlag$ut = false;
	private double sum;
	private boolean system$gibbsForward = true;
	private double[] ut;

	public DiscreteChoice$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final int[] get$ObsChoices() {
		return ObsChoices;
	}

	@Override
	public final void set$ObsChoices(int[] cv$value) {
		ObsChoices = cv$value;
	}

	@Override
	public final int[] get$choices() {
		return choices;
	}

	@Override
	public final void set$choices(int[] cv$value) {
		choices = cv$value;
		setFlag$choices = true;
		fixedProbFlag$sample81 = false;
	}

	@Override
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final boolean get$fixedFlag$sample27() {
		return fixedFlag$sample27;
	}

	@Override
	public final void set$fixedFlag$sample27(boolean cv$value) {
		fixedFlag$sample27 = cv$value;
		fixedProbFlag$sample27 = (fixedFlag$sample27 && fixedProbFlag$sample27);
		fixedProbFlag$sample81 = (fixedFlag$sample27 && fixedProbFlag$sample81);
	}

	@Override
	public final boolean get$fixedFlag$sample81() {
		return fixedFlag$sample81;
	}

	@Override
	public final void set$fixedFlag$sample81(boolean cv$value) {
		fixedFlag$sample81 = cv$value;
		fixedProbFlag$sample81 = (fixedFlag$sample81 && fixedProbFlag$sample81);
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
	public final double get$logProbability$choices() {
		return logProbability$choices;
	}

	@Override
	public final double get$logProbability$exped() {
		return logProbability$exped;
	}

	@Override
	public final double get$logProbability$prob() {
		return logProbability$prob;
	}

	@Override
	public final double get$logProbability$sum() {
		return logProbability$sum;
	}

	@Override
	public final double get$logProbability$ut() {
		return logProbability$ut;
	}

	@Override
	public final int get$noObs() {
		return noObs;
	}

	@Override
	public final void set$noObs(int cv$value) {
		noObs = cv$value;
	}

	@Override
	public final int get$noProducts() {
		return noProducts;
	}

	@Override
	public final void set$noProducts(int cv$value) {
		noProducts = cv$value;
	}

	@Override
	public final double[] get$prob() {
		return prob;
	}

	@Override
	public final double get$sum() {
		return sum;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
		fixedProbFlag$sample27 = false;
		fixedProbFlag$sample81 = false;
	}

	private final void logProbabilityValue$sample27() {
		if(!fixedProbFlag$sample27) {
			double cv$accumulator = 0.0;
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[i$var20];
					{
						{
							double var23 = 0.0;
							double var24 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var23) / Math.sqrt(var24))) - (0.5 * Math.log(var24))));
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
				logProbability$var25[((i$var20 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample27[((i$var20 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$prob = false;
				{
					for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
						if((i$var20 == i$var38)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				{
					for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
						if((i$var20 == i$var38)) {
							if(((0 <= i$var38) && (i$var38 < noProducts))) {
								{
									if(!cv$guard$sum) {
										cv$guard$sum = true;
										logProbability$sum = (logProbability$sum + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
				{
					for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
						if((i$var20 == i$var38)) {
							if(((0 <= i$var38) && (i$var38 < noProducts))) {
								{
									for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
						if((i$var20 == i$var38)) {
							for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
								if((i$var38 == i$var63)) {
									if(!cv$guard$prob) {
										cv$guard$prob = true;
										logProbability$prob = (logProbability$prob + cv$sampleProbability);
									}
								}
							}
						}
					}
				}
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample27 = fixedFlag$sample27;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample27[((i$var20 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var25[((i$var20 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$prob = false;
				{
					for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
						if((i$var20 == i$var38)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				{
					for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
						if((i$var20 == i$var38)) {
							if(((0 <= i$var38) && (i$var38 < noProducts))) {
								{
									if(!cv$guard$sum) {
										cv$guard$sum = true;
										logProbability$sum = (logProbability$sum + cv$sampleValue);
									}
								}
							}
						}
					}
				}
				{
					for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
						if((i$var20 == i$var38)) {
							if(((0 <= i$var38) && (i$var38 < noProducts))) {
								{
									for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
						if((i$var20 == i$var38)) {
							for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
								if((i$var38 == i$var63)) {
									if(!cv$guard$prob) {
										cv$guard$prob = true;
										logProbability$prob = (logProbability$prob + cv$sampleValue);
									}
								}
							}
						}
					}
				}
			}
			logProbability$ut = (logProbability$ut + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample27)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample81() {
		if(!fixedProbFlag$sample81) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var78 = 0; var78 < noObs; var78 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = choices[var78];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < prob.length))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var67 = cv$sampleAccumulator;
			logProbability$var79 = cv$sampleAccumulator;
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample81 = (fixedFlag$sample81 && fixedFlag$sample27);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var79;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var67 = cv$rvAccumulator;
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample27(int i$var20) {
		int cv$noStates = 0;
		{
			cv$noStates = Math.max(cv$noStates, 2);
		}
		double cv$originalValue = ut[i$var20];
		double cv$originalProbability = 0.0;
		double cv$var = ((cv$originalValue * cv$originalValue) * (0.1 * 0.1));
		if((cv$var < (0.1 * 0.1)))
			cv$var = (0.1 * 0.1);
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		double cv$proposedProbability = 0.0;
		for(int cv$valuePos = 0; cv$valuePos < cv$noStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$currentValue;
			if((cv$valuePos == 0))
				cv$currentValue = cv$originalValue;
			else {
				cv$currentValue = cv$proposedValue;
				{
					double var26 = cv$proposedValue;
					ut[i$var20] = cv$currentValue;
					{
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var20 == i$var38)) {
								{
									exped[i$var38] = Math.exp(ut[i$var38]);
								}
							}
						}
					}
					{
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var20 == i$var38)) {
								if(((0 <= i$var38) && (i$var38 < noProducts))) {
									{
										{
											double reduceVar$sum$8 = 0.0;
											for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1) {
												double i$var49 = reduceVar$sum$8;
												double j = exped[cv$reduction47Index];
												reduceVar$sum$8 = (i$var49 + j);
											}
											sum = reduceVar$sum$8;
										}
									}
								}
							}
						}
					}
					{
						boolean[] guard$sample27put68 = guard$sample27put68$global;
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var20 == i$var38)) {
								if(((0 <= i$var38) && (i$var38 < noProducts))) {
									{
										for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1)
											guard$sample27put68[((i$var63 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var20 == i$var38)) {
								for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
									if((i$var38 == i$var63))
										guard$sample27put68[((i$var63 - 0) / 1)] = false;
								}
							}
						}
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var20 == i$var38)) {
								if(((0 <= i$var38) && (i$var38 < noProducts))) {
									{
										for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
											if(!guard$sample27put68[((i$var63 - 0) / 1)]) {
												guard$sample27put68[((i$var63 - 0) / 1)] = true;
												{
													prob[i$var63] = (exped[i$var63] / sum);
												}
											}
										}
									}
								}
							}
						}
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var20 == i$var38)) {
								for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
									if((i$var38 == i$var63)) {
										if(!guard$sample27put68[((i$var63 - 0) / 1)]) {
											guard$sample27put68[((i$var63 - 0) / 1)] = true;
											{
												prob[i$var63] = (exped[i$var63] / sum);
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
				double cv$temp$0$var23;
				{
					cv$temp$0$var23 = 0.0;
				}
				double cv$temp$1$var24;
				{
					cv$temp$1$var24 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var23) / Math.sqrt(cv$temp$1$var24))) - (0.5 * Math.log(cv$temp$1$var24))));
				{
					{
						boolean guard$sample27categorical69 = false;
						double traceTempVariable$var39$7_1 = cv$currentValue;
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var20 == i$var38)) {
								double traceTempVariable$i$7_3 = Math.exp(traceTempVariable$var39$7_1);
								if(((0 <= i$var38) && (i$var38 < noProducts))) {
									{
										if((0 < noProducts)) {
											double reduceVar$sum$9 = 0.0;
											for(int cv$reduction736Index = 0; cv$reduction736Index < i$var38; cv$reduction736Index += 1) {
												double i$var49 = reduceVar$sum$9;
												double j = exped[cv$reduction736Index];
												reduceVar$sum$9 = (i$var49 + j);
											}
											for(int cv$reduction736Index = (i$var38 + 1); cv$reduction736Index < noProducts; cv$reduction736Index += 1) {
												double i$var49 = reduceVar$sum$9;
												double j = exped[cv$reduction736Index];
												reduceVar$sum$9 = (i$var49 + j);
											}
											double cv$reduced47 = reduceVar$sum$9;
											reduceVar$sum$9 = (traceTempVariable$i$7_3 + cv$reduced47);
											double traceTempVariable$sum$7_4 = reduceVar$sum$9;
											if(!guard$sample27categorical69) {
												guard$sample27categorical69 = true;
												{
													for(int var78 = 0; var78 < noObs; var78 += 1) {
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	{
																		double[] cv$temp$2$prob;
																		{
																			cv$temp$2$prob = prob;
																		}
																		if(((Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var78]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var78]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var78]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var78]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var78]]):Double.NEGATIVE_INFINITY)));
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
						double traceTempVariable$var39$8_1 = cv$currentValue;
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var20 == i$var38)) {
								double traceTempVariable$var64$8_3 = Math.exp(traceTempVariable$var39$8_1);
								for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
									if((i$var38 == i$var63)) {
										if(!guard$sample27categorical69) {
											guard$sample27categorical69 = true;
											{
												for(int var78 = 0; var78 < noObs; var78 += 1) {
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double[] cv$temp$3$prob;
																	{
																		cv$temp$3$prob = prob;
																	}
																	if(((Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var78]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var78]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var78]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var78]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var78]) && (choices[var78] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var78]]):Double.NEGATIVE_INFINITY)));
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
			double var26 = cv$originalValue;
			ut[i$var20] = var26;
			{
				for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
					if((i$var20 == i$var38)) {
						{
							exped[i$var38] = Math.exp(ut[i$var38]);
						}
					}
				}
			}
			{
				for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
					if((i$var20 == i$var38)) {
						if(((0 <= i$var38) && (i$var38 < noProducts))) {
							{
								{
									double reduceVar$sum$10 = 0.0;
									for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1) {
										double i$var49 = reduceVar$sum$10;
										double j = exped[cv$reduction47Index];
										reduceVar$sum$10 = (i$var49 + j);
									}
									sum = reduceVar$sum$10;
								}
							}
						}
					}
				}
			}
			{
				boolean[] guard$sample27put68 = guard$sample27put68$global;
				for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
					if((i$var20 == i$var38)) {
						if(((0 <= i$var38) && (i$var38 < noProducts))) {
							{
								for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1)
									guard$sample27put68[((i$var63 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
					if((i$var20 == i$var38)) {
						for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
							if((i$var38 == i$var63))
								guard$sample27put68[((i$var63 - 0) / 1)] = false;
						}
					}
				}
				for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
					if((i$var20 == i$var38)) {
						if(((0 <= i$var38) && (i$var38 < noProducts))) {
							{
								for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
									if(!guard$sample27put68[((i$var63 - 0) / 1)]) {
										guard$sample27put68[((i$var63 - 0) / 1)] = true;
										{
											prob[i$var63] = (exped[i$var63] / sum);
										}
									}
								}
							}
						}
					}
				}
				for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
					if((i$var20 == i$var38)) {
						for(int i$var63 = 0; i$var63 < noProducts; i$var63 += 1) {
							if((i$var38 == i$var63)) {
								if(!guard$sample27put68[((i$var63 - 0) / 1)]) {
									guard$sample27put68[((i$var63 - 0) / 1)] = true;
									{
										prob[i$var63] = (exped[i$var63] / sum);
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
	public final void allocateScratch() {
		int cv$max_i$var63 = 0;
		cv$max_i$var63 = Math.max(cv$max_i$var63, ((noProducts - 0) / 1));
		guard$sample27put68$global = new boolean[cv$max_i$var63];
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut) {
			{
				ut = new double[noProducts];
			}
		}
		{
			exped = new double[noProducts];
		}
		{
			prob = new double[noProducts];
		}
		if(!setFlag$choices) {
			{
				choices = new int[noObs];
			}
		}
		{
			logProbability$var25 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample27 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var20, int forEnd$i$var20, int threadID$i$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var20 = forStart$i$var20; i$var20 < forEnd$i$var20; i$var20 += 1) {
						if(!fixedFlag$sample27)
							ut[i$var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample27)
							exped[i$var38] = Math.exp(ut[i$var38]);
					}
			}
		);
		double reduceVar$sum$11 = 0.0;
		for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1) {
			double i$var49 = reduceVar$sum$11;
			double j = exped[cv$reduction47Index];
			if(!fixedFlag$sample27)
				reduceVar$sum$11 = (i$var49 + j);
		}
		if(!fixedFlag$sample27)
			sum = reduceVar$sum$11;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var63, int forEnd$i$var63, int threadID$i$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var63 = forStart$i$var63; i$var63 < forEnd$i$var63; i$var63 += 1) {
						if(!fixedFlag$sample27)
							prob[i$var63] = (exped[i$var63] / sum);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var78, int forEnd$var78, int threadID$var78, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var78 = forStart$var78; var78 < forEnd$var78; var78 += 1) {
						if(!fixedFlag$sample81)
							choices[var78] = DistributionSampling.sampleCategorical(RNG$1, prob);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var20, int forEnd$i$var20, int threadID$i$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var20 = forStart$i$var20; i$var20 < forEnd$i$var20; i$var20 += 1) {
						if(!fixedFlag$sample27)
							ut[i$var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample27)
							exped[i$var38] = Math.exp(ut[i$var38]);
					}
			}
		);
		double reduceVar$sum$13 = 0.0;
		for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1) {
			double i$var49 = reduceVar$sum$13;
			double j = exped[cv$reduction47Index];
			if(!fixedFlag$sample27)
				reduceVar$sum$13 = (i$var49 + j);
		}
		if(!fixedFlag$sample27)
			sum = reduceVar$sum$13;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var63, int forEnd$i$var63, int threadID$i$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var63 = forStart$i$var63; i$var63 < forEnd$i$var63; i$var63 += 1) {
						if(!fixedFlag$sample27)
							prob[i$var63] = (exped[i$var63] / sum);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var20, int forEnd$i$var20, int threadID$i$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var20 = forStart$i$var20; i$var20 < forEnd$i$var20; i$var20 += 1) {
						if(!fixedFlag$sample27)
							ut[i$var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample27)
							exped[i$var38] = Math.exp(ut[i$var38]);
					}
			}
		);
		double reduceVar$sum$12 = 0.0;
		for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1) {
			double i$var49 = reduceVar$sum$12;
			double j = exped[cv$reduction47Index];
			if(!fixedFlag$sample27)
				reduceVar$sum$12 = (i$var49 + j);
		}
		if(!fixedFlag$sample27)
			sum = reduceVar$sum$12;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var63, int forEnd$i$var63, int threadID$i$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var63 = forStart$i$var63; i$var63 < forEnd$i$var63; i$var63 += 1) {
						if(!fixedFlag$sample27)
							prob[i$var63] = (exped[i$var63] / sum);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1) {
				if(!fixedFlag$sample27)
					sample27(i$var20);
			}
		} else {
			for(int i$var20 = (noProducts - ((((noProducts - 1) - 1) % 1) + 1)); i$var20 >= ((1 - 1) + 1); i$var20 -= 1) {
				if(!fixedFlag$sample27)
					sample27(i$var20);
			}
		}
		system$gibbsForward = !system$gibbsForward;
	}

	@Override
	public final void initializeConstants() {
		ut[0] = 0.0;
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1)
			logProbability$var25[((i$var20 - 1) / 1)] = 0.0;
		logProbability$prob = 0.0;
		logProbability$sum = 0.0;
		logProbability$exped = 0.0;
		logProbability$ut = 0.0;
		if(!fixedProbFlag$sample27) {
			for(int i$var20 = 1; i$var20 < noProducts; i$var20 += 1)
				logProbability$sample27[((i$var20 - 1) / 1)] = 0.0;
		}
		logProbability$var67 = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample81)
			logProbability$var79 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample27)
			logProbabilityValue$sample27();
		logProbabilityValue$sample81();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample27();
		logProbabilityValue$sample81();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample27();
		logProbabilityValue$sample81();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var20, int forEnd$i$var20, int threadID$i$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var20 = forStart$i$var20; i$var20 < forEnd$i$var20; i$var20 += 1) {
						if(!fixedFlag$sample27)
							ut[i$var20] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample27)
							exped[i$var38] = Math.exp(ut[i$var38]);
					}
			}
		);
		double reduceVar$sum$14 = 0.0;
		for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1) {
			double i$var49 = reduceVar$sum$14;
			double j = exped[cv$reduction47Index];
			if(!fixedFlag$sample27)
				reduceVar$sum$14 = (i$var49 + j);
		}
		if(!fixedFlag$sample27)
			sum = reduceVar$sum$14;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var63, int forEnd$i$var63, int threadID$i$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var63 = forStart$i$var63; i$var63 < forEnd$i$var63; i$var63 += 1) {
						if(!fixedFlag$sample27)
							prob[i$var63] = (exped[i$var63] / sum);
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propogateObservedValues() {
		int[] cv$source1 = ObsChoices;
		int[] cv$target1 = choices;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(setFlag$ut)
							exped[i$var38] = Math.exp(ut[i$var38]);
					}
			}
		);
		if(setFlag$ut) {
			double reduceVar$sum$15 = 0.0;
			for(int cv$reduction47Index = 0; cv$reduction47Index < noProducts; cv$reduction47Index += 1) {
				double i$var49 = reduceVar$sum$15;
				double j = exped[cv$reduction47Index];
				reduceVar$sum$15 = (i$var49 + j);
			}
			sum = reduceVar$sum$15;
		}
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var63, int forEnd$i$var63, int threadID$i$var63, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var63 = forStart$i$var63; i$var63 < forEnd$i$var63; i$var63 += 1) {
						if(setFlag$ut)
							prob[i$var63] = (exped[i$var63] / sum);
					}
			}
		);
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
		     + "public model DiscreteChoice(int noProducts, int noObs, int[] ObsChoices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;\n"
		     + "    for(int i=1; i<noProducts; i++) {\n"
		     + "        ut[i]= gaussian(0, 10).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    // calculate choice probabilities\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int i : [0..noProducts)) {\n"
		     + "        exped[i] = exp(ut[i]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n"
		     + "    double[] prob = new double[noProducts];\n"
		     + "    for (int i : [0..noProducts)) {\n"
		     + "        prob[i] = exped[i] / sum;\n"
		     + "    }\n"
		     + "    // draw consumer choices according to the calculated probabilities\n"
		     + "    int[] choices = categorical(prob).sample(noObs);\n"
		     + "\n"
		     + "    // assert generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}