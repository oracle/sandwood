package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoice$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoice$CoreInterface {
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample24 = false;
	private boolean fixedProbFlag$sample24 = false;
	private boolean fixedProbFlag$sample78 = false;
	private boolean[] guard$sample24put65$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample24;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[] logProbability$var23;
	private double logProbability$var65;
	private double logProbability$var77;
	private int noObs;
	private int noProducts;
	private double[] prob;
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
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final boolean get$fixedFlag$sample24() {
		return fixedFlag$sample24;
	}

	@Override
	public final void set$fixedFlag$sample24(boolean cv$value) {
		fixedFlag$sample24 = cv$value;
		fixedProbFlag$sample24 = (fixedFlag$sample24 && fixedProbFlag$sample24);
		fixedProbFlag$sample78 = (fixedFlag$sample24 && fixedProbFlag$sample78);
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
		fixedProbFlag$sample24 = false;
		fixedProbFlag$sample78 = false;
	}

	private final void logProbabilityValue$sample24() {
		if(!fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[i$var18];
					{
						{
							double var21 = 0.0;
							double var22 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var21) / Math.sqrt(var22))) - (0.5 * Math.log(var22))));
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
				logProbability$var23[((i$var18 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample24[((i$var18 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$prob = false;
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(((0 <= i$var36) && (i$var36 < noProducts))) {
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
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(((0 <= i$var36) && (i$var36 < noProducts))) {
								{
									for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
								if((i$var36 == i$var61)) {
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
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample24 = fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample24[((i$var18 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var23[((i$var18 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$prob = false;
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				{
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(((0 <= i$var36) && (i$var36 < noProducts))) {
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
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							if(((0 <= i$var36) && (i$var36 < noProducts))) {
								{
									for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
						if((i$var18 == i$var36)) {
							for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
								if((i$var36 == i$var61)) {
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
			if(fixedFlag$sample24)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!fixedProbFlag$sample78) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var76 = 0; var76 < noObs; var76 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = choices[var76];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= cv$sampleValue) && (cv$sampleValue < noProducts))?Math.log(prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var65 = cv$sampleAccumulator;
			logProbability$var77 = cv$sampleAccumulator;
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample78 = fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var77;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var65 = cv$rvAccumulator;
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample24(int i$var18) {
		int cv$numNumStates = 0;
		{
			cv$numNumStates = Math.max(cv$numNumStates, 2);
		}
		double cv$originalValue = ut[i$var18];
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
					{
						{
							ut[i$var18] = cv$currentValue;
						}
					}
					{
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								{
									exped[i$var36] = Math.exp(ut[i$var36]);
								}
							}
						}
					}
					{
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < noProducts))) {
									{
										{
											double reduceVar$sum$8 = 0.0;
											for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
												double i$var47 = reduceVar$sum$8;
												double j = exped[cv$reduction44Index];
												reduceVar$sum$8 = (i$var47 + j);
											}
											sum = reduceVar$sum$8;
										}
									}
								}
							}
						}
					}
					{
						boolean[] guard$sample24put65 = guard$sample24put65$global;
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < noProducts))) {
									{
										for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
											guard$sample24put65[((i$var61 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
									if((i$var36 == i$var61))
										guard$sample24put65[((i$var61 - 0) / 1)] = false;
								}
							}
						}
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								if(((0 <= i$var36) && (i$var36 < noProducts))) {
									{
										for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
											if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
												guard$sample24put65[((i$var61 - 0) / 1)] = true;
												{
													prob[i$var61] = (exped[i$var61] / sum);
												}
											}
										}
									}
								}
							}
						}
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
									if((i$var36 == i$var61)) {
										if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
											guard$sample24put65[((i$var61 - 0) / 1)] = true;
											{
												prob[i$var61] = (exped[i$var61] / sum);
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
				double cv$temp$0$var21;
				{
					cv$temp$0$var21 = 0.0;
				}
				double cv$temp$1$var22;
				{
					cv$temp$1$var22 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var21) / Math.sqrt(cv$temp$1$var22))) - (0.5 * Math.log(cv$temp$1$var22))));
				{
					{
						boolean guard$sample24categorical66 = false;
						double traceTempVariable$var37$8_1 = cv$currentValue;
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								double traceTempVariable$i$8_3 = Math.exp(traceTempVariable$var37$8_1);
								if(((0 <= i$var36) && (i$var36 < noProducts))) {
									{
										if((0 < noProducts)) {
											double reduceVar$sum$9 = 0.0;
											for(int cv$reduction718Index = 0; cv$reduction718Index < i$var36; cv$reduction718Index += 1) {
												double i$var47 = reduceVar$sum$9;
												double j = exped[cv$reduction718Index];
												reduceVar$sum$9 = (i$var47 + j);
											}
											for(int cv$reduction718Index = (i$var36 + 1); cv$reduction718Index < noProducts; cv$reduction718Index += 1) {
												double i$var47 = reduceVar$sum$9;
												double j = exped[cv$reduction718Index];
												reduceVar$sum$9 = (i$var47 + j);
											}
											double cv$reduced44 = reduceVar$sum$9;
											reduceVar$sum$9 = (traceTempVariable$i$8_3 + cv$reduced44);
											double traceTempVariable$sum$8_4 = reduceVar$sum$9;
											if(!guard$sample24categorical66) {
												guard$sample24categorical66 = true;
												{
													for(int var76 = 0; var76 < noObs; var76 += 1) {
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
																		int cv$temp$3$$var509;
																		{
																			int $var509 = noProducts;
																			cv$temp$3$$var509 = $var509;
																		}
																		if(((Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var509))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var509))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var509))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var509))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$3$$var509))?Math.log(cv$temp$2$prob[choices[var76]]):Double.NEGATIVE_INFINITY)));
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
						double traceTempVariable$var37$9_1 = cv$currentValue;
						for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
							if((i$var18 == i$var36)) {
								double traceTempVariable$var62$9_3 = Math.exp(traceTempVariable$var37$9_1);
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
									if((i$var36 == i$var61)) {
										if(!guard$sample24categorical66) {
											guard$sample24categorical66 = true;
											{
												for(int var76 = 0; var76 < noObs; var76 += 1) {
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	double[] cv$temp$4$prob;
																	{
																		cv$temp$4$prob = prob;
																	}
																	int cv$temp$5$$var510;
																	{
																		int $var510 = noProducts;
																		cv$temp$5$$var510 = $var510;
																	}
																	if(((Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var510))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var510))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var510))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var510))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var76]) && (choices[var76] < cv$temp$5$$var510))?Math.log(cv$temp$4$prob[choices[var76]]):Double.NEGATIVE_INFINITY)));
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
			double var24 = cv$originalValue;
			{
				{
					ut[i$var18] = var24;
				}
			}
			{
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						{
							exped[i$var36] = Math.exp(ut[i$var36]);
						}
					}
				}
			}
			{
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < noProducts))) {
							{
								{
									double reduceVar$sum$10 = 0.0;
									for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
										double i$var47 = reduceVar$sum$10;
										double j = exped[cv$reduction44Index];
										reduceVar$sum$10 = (i$var47 + j);
									}
									sum = reduceVar$sum$10;
								}
							}
						}
					}
				}
			}
			{
				boolean[] guard$sample24put65 = guard$sample24put65$global;
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < noProducts))) {
							{
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1)
									guard$sample24put65[((i$var61 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
							if((i$var36 == i$var61))
								guard$sample24put65[((i$var61 - 0) / 1)] = false;
						}
					}
				}
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						if(((0 <= i$var36) && (i$var36 < noProducts))) {
							{
								for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
									if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
										guard$sample24put65[((i$var61 - 0) / 1)] = true;
										{
											prob[i$var61] = (exped[i$var61] / sum);
										}
									}
								}
							}
						}
					}
				}
				for(int i$var36 = 0; i$var36 < noProducts; i$var36 += 1) {
					if((i$var18 == i$var36)) {
						for(int i$var61 = 0; i$var61 < noProducts; i$var61 += 1) {
							if((i$var36 == i$var61)) {
								if(!guard$sample24put65[((i$var61 - 0) / 1)]) {
									guard$sample24put65[((i$var61 - 0) / 1)] = true;
									{
										prob[i$var61] = (exped[i$var61] / sum);
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
		int cv$max_i$var61 = 0;
		cv$max_i$var61 = Math.max(cv$max_i$var61, ((noProducts - 0) / 1));
		guard$sample24put65$global = new boolean[cv$max_i$var61];
	}

	@Override
	public final void allocator() {
		if(!fixedFlag$sample24) {
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
		{
			choices = new int[noObs];
		}
		{
			logProbability$var23 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample24 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!fixedFlag$sample24)
							ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1) {
						if(!fixedFlag$sample24)
							exped[i$var36] = Math.exp(ut[i$var36]);
					}
			}
		);
		double reduceVar$sum$11 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$11;
			double j = exped[cv$reduction44Index];
			if(!fixedFlag$sample24)
				reduceVar$sum$11 = (i$var47 + j);
		}
		if(!fixedFlag$sample24)
			sum = reduceVar$sum$11;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1) {
						if(!fixedFlag$sample24)
							prob[i$var61] = (exped[i$var61] / sum);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
						choices[var76] = DistributionSampling.sampleCategorical(RNG$1, prob, noProducts);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!fixedFlag$sample24)
							ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1) {
						if(!fixedFlag$sample24)
							exped[i$var36] = Math.exp(ut[i$var36]);
					}
			}
		);
		double reduceVar$sum$13 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$13;
			double j = exped[cv$reduction44Index];
			if(!fixedFlag$sample24)
				reduceVar$sum$13 = (i$var47 + j);
		}
		if(!fixedFlag$sample24)
			sum = reduceVar$sum$13;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1) {
						if(!fixedFlag$sample24)
							prob[i$var61] = (exped[i$var61] / sum);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!fixedFlag$sample24)
							ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1) {
						if(!fixedFlag$sample24)
							exped[i$var36] = Math.exp(ut[i$var36]);
					}
			}
		);
		double reduceVar$sum$12 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$12;
			double j = exped[cv$reduction44Index];
			if(!fixedFlag$sample24)
				reduceVar$sum$12 = (i$var47 + j);
		}
		if(!fixedFlag$sample24)
			sum = reduceVar$sum$12;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1) {
						if(!fixedFlag$sample24)
							prob[i$var61] = (exped[i$var61] / sum);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1) {
				if(!fixedFlag$sample24)
					sample24(i$var18);
			}
		} else {
			for(int i$var18 = (noProducts - ((((noProducts - 1) - 1) % 1) + 1)); i$var18 >= ((1 - 1) + 1); i$var18 -= 1) {
				if(!fixedFlag$sample24)
					sample24(i$var18);
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
		for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
			logProbability$var23[((i$var18 - 1) / 1)] = 0.0;
		logProbability$ut = 0.0;
		logProbability$exped = 0.0;
		logProbability$sum = 0.0;
		logProbability$prob = 0.0;
		if(!fixedProbFlag$sample24) {
			for(int i$var18 = 1; i$var18 < noProducts; i$var18 += 1)
				logProbability$sample24[((i$var18 - 1) / 1)] = 0.0;
		}
		logProbability$var65 = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample78)
			logProbability$var77 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample24)
			logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1) {
						if(!fixedFlag$sample24)
							ut[i$var18] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1) {
						if(!fixedFlag$sample24)
							exped[i$var36] = Math.exp(ut[i$var36]);
					}
			}
		);
		double reduceVar$sum$14 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$14;
			double j = exped[cv$reduction44Index];
			if(!fixedFlag$sample24)
				reduceVar$sum$14 = (i$var47 + j);
		}
		if(!fixedFlag$sample24)
			sum = reduceVar$sum$14;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1) {
						if(!fixedFlag$sample24)
							prob[i$var61] = (exped[i$var61] / sum);
					}
			}
		);
		logModelProbabilitiesVal();
	}

	@Override
	public final void propagateObservedValues() {
		int[] cv$source1 = ObsChoices;
		int[] cv$target1 = choices;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1) {
						if(fixedFlag$sample24)
							exped[i$var36] = Math.exp(ut[i$var36]);
					}
			}
		);
		double reduceVar$sum$15 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < noProducts; cv$reduction44Index += 1) {
			double i$var47 = reduceVar$sum$15;
			double j = exped[cv$reduction44Index];
			if(fixedFlag$sample24)
				reduceVar$sum$15 = (i$var47 + j);
		}
		if(fixedFlag$sample24)
			sum = reduceVar$sum$15;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1) {
						if(fixedFlag$sample24)
							prob[i$var61] = (exped[i$var61] / sum);
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