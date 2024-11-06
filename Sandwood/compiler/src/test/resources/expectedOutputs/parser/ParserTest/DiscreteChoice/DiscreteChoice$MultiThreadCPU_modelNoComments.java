package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

class DiscreteChoice$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements DiscreteChoice$CoreInterface {
	private int[] ObsChoices;
	private int[] choices;
	private double[] exped;
	private boolean fixedFlag$sample19 = false;
	private boolean fixedFlag$sample49 = false;
	private boolean fixedProbFlag$sample19 = false;
	private boolean fixedProbFlag$sample49 = false;
	private boolean[] guard$sample19put43$global;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$choices;
	private double logProbability$exped;
	private double logProbability$prob;
	private double[] logProbability$sample19;
	private double logProbability$sum;
	private double logProbability$ut;
	private double[] logProbability$var17;
	private double logProbability$var42;
	private double logProbability$var47;
	private int noObs;
	private int noProducts;
	private double[] prob;
	private boolean setFlag$choices = false;
	private boolean setFlag$exped = false;
	private boolean setFlag$prob = false;
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
	}

	@Override
	public final double[] get$exped() {
		return exped;
	}

	@Override
	public final void set$exped(double[] cv$value) {
		exped = cv$value;
		setFlag$exped = true;
	}

	@Override
	public final boolean get$fixedFlag$sample19() {
		return fixedFlag$sample19;
	}

	@Override
	public final void set$fixedFlag$sample19(boolean cv$value) {
		fixedFlag$sample19 = cv$value;
		fixedProbFlag$sample19 = (fixedFlag$sample19 && fixedProbFlag$sample19);
		fixedProbFlag$sample49 = (fixedFlag$sample19 && fixedProbFlag$sample49);
	}

	@Override
	public final boolean get$fixedFlag$sample49() {
		return fixedFlag$sample49;
	}

	@Override
	public final void set$fixedFlag$sample49(boolean cv$value) {
		fixedFlag$sample49 = cv$value;
		fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedProbFlag$sample49);
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
	public final void set$prob(double[] cv$value) {
		prob = cv$value;
		setFlag$prob = true;
	}

	@Override
	public final double get$sum() {
		return sum;
	}

	@Override
	public final void set$sum(double cv$value) {
		sum = cv$value;
	}

	@Override
	public final double[] get$ut() {
		return ut;
	}

	@Override
	public final void set$ut(double[] cv$value) {
		ut = cv$value;
		setFlag$ut = true;
	}

	private final void logProbabilityValue$sample19() {
		if(!fixedProbFlag$sample19) {
			double cv$accumulator = 0.0;
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					double cv$sampleValue = ut[i$var12];
					{
						{
							double var15 = 0.0;
							double var16 = 10.0;
							double cv$weightedProbability = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var15) / Math.sqrt(var16))) - (0.5 * Math.log(var16))));
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
				logProbability$var17[((i$var12 - 1) / 1)] = cv$sampleAccumulator;
				logProbability$sample19[((i$var12 - 1) / 1)] = cv$sampleProbability;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$prob = false;
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleProbability);
							}
						}
					}
				}
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(((0 <= i$var23) && (i$var23 < noProducts))) {
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
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(((0 <= i$var23) && (i$var23 < noProducts))) {
								{
									for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleProbability);
										}
									}
								}
							}
						}
					}
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
								if((i$var23 == i$var38)) {
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
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample19 = fixedFlag$sample19;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample19[((i$var12 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				logProbability$var17[((i$var12 - 1) / 1)] = cv$rvAccumulator;
				boolean cv$guard$exped = false;
				boolean cv$guard$sum = false;
				boolean cv$guard$prob = false;
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(!cv$guard$exped) {
								cv$guard$exped = true;
								logProbability$exped = (logProbability$exped + cv$sampleValue);
							}
						}
					}
				}
				{
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(((0 <= i$var23) && (i$var23 < noProducts))) {
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
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							if(((0 <= i$var23) && (i$var23 < noProducts))) {
								{
									for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
										if(!cv$guard$prob) {
											cv$guard$prob = true;
											logProbability$prob = (logProbability$prob + cv$sampleValue);
										}
									}
								}
							}
						}
					}
					for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
						if((i$var12 == i$var23)) {
							for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
								if((i$var23 == i$var38)) {
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
			if(fixedFlag$sample19)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample49() {
		if(!fixedProbFlag$sample49) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < noObs; var46 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					int cv$sampleValue = choices[var46];
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
			logProbability$var42 = cv$sampleAccumulator;
			logProbability$var47 = cv$sampleAccumulator;
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample49 = (fixedFlag$sample49 && fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var47;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$var42 = cv$rvAccumulator;
			logProbability$choices = (logProbability$choices + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void sample19(int i$var12) {
		double cv$originalValue = ut[i$var12];
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
					double var18 = cv$proposedValue;
					ut[i$var12] = cv$currentValue;
					{
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								{
									exped[i$var23] = Math.exp(ut[i$var23]);
								}
							}
						}
					}
					{
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								if(((0 <= i$var23) && (i$var23 < noProducts))) {
									{
										{
											double reduceVar$sum$8 = 0.0;
											for(int cv$reduction373Index = 0; cv$reduction373Index < noProducts; cv$reduction373Index += 1) {
												double i$var31 = reduceVar$sum$8;
												double j = exped[cv$reduction373Index];
												reduceVar$sum$8 = (i$var31 + j);
											}
											sum = reduceVar$sum$8;
										}
									}
								}
							}
						}
					}
					{
						boolean[] guard$sample19put43 = guard$sample19put43$global;
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								if(((0 <= i$var23) && (i$var23 < noProducts))) {
									{
										for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
											guard$sample19put43[((i$var38 - 0) / 1)] = false;
									}
								}
							}
						}
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
									if((i$var23 == i$var38))
										guard$sample19put43[((i$var38 - 0) / 1)] = false;
								}
							}
						}
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								if(((0 <= i$var23) && (i$var23 < noProducts))) {
									{
										for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
											if(!guard$sample19put43[((i$var38 - 0) / 1)]) {
												guard$sample19put43[((i$var38 - 0) / 1)] = true;
												{
													prob[i$var38] = (exped[i$var38] / sum);
												}
											}
										}
									}
								}
							}
						}
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
									if((i$var23 == i$var38)) {
										if(!guard$sample19put43[((i$var38 - 0) / 1)]) {
											guard$sample19put43[((i$var38 - 0) / 1)] = true;
											{
												prob[i$var38] = (exped[i$var38] / sum);
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
				double cv$temp$0$var15;
				{
					cv$temp$0$var15 = 0.0;
				}
				double cv$temp$1$var16;
				{
					cv$temp$1$var16 = 10.0;
				}
				double cv$accumulatedProbabilities = (Math.log(1.0) + (DistributionSampling.logProbabilityGaussian(((cv$currentValue - cv$temp$0$var15) / Math.sqrt(cv$temp$1$var16))) - (0.5 * Math.log(cv$temp$1$var16))));
				{
					{
						boolean guard$sample19categorical44 = false;
						double traceTempVariable$var24$7_1 = cv$currentValue;
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								double traceTempVariable$i$7_3 = Math.exp(traceTempVariable$var24$7_1);
								if(((0 <= i$var23) && (i$var23 < noProducts))) {
									{
										if((0 < noProducts)) {
											double reduceVar$sum$9 = 0.0;
											for(int cv$reduction438Index = 0; cv$reduction438Index < i$var23; cv$reduction438Index += 1) {
												double i$var31 = reduceVar$sum$9;
												double j = exped[cv$reduction438Index];
												reduceVar$sum$9 = (i$var31 + j);
											}
											for(int cv$reduction438Index = (i$var23 + 1); cv$reduction438Index < noProducts; cv$reduction438Index += 1) {
												double i$var31 = reduceVar$sum$9;
												double j = exped[cv$reduction438Index];
												reduceVar$sum$9 = (i$var31 + j);
											}
											double cv$reduced32 = reduceVar$sum$9;
											reduceVar$sum$9 = (traceTempVariable$i$7_3 + cv$reduced32);
											double traceTempVariable$sum$7_4 = reduceVar$sum$9;
											if(!guard$sample19categorical44) {
												guard$sample19categorical44 = true;
												{
													for(int var46 = 0; var46 < noObs; var46 += 1) {
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
																		if(((Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$2$prob.length))?Math.log(cv$temp$2$prob[choices[var46]]):Double.NEGATIVE_INFINITY)));
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
						double traceTempVariable$var24$8_1 = cv$currentValue;
						for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
							if((i$var12 == i$var23)) {
								double traceTempVariable$var39$8_3 = Math.exp(traceTempVariable$var24$8_1);
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
									if((i$var23 == i$var38)) {
										if(!guard$sample19categorical44) {
											guard$sample19categorical44 = true;
											{
												for(int var46 = 0; var46 < noObs; var46 += 1) {
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
																	if(((Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= choices[var46]) && (choices[var46] < cv$temp$3$prob.length))?Math.log(cv$temp$3$prob[choices[var46]]):Double.NEGATIVE_INFINITY)));
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
			double var18 = cv$originalValue;
			ut[i$var12] = var18;
			{
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						{
							exped[i$var23] = Math.exp(ut[i$var23]);
						}
					}
				}
			}
			{
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						if(((0 <= i$var23) && (i$var23 < noProducts))) {
							{
								{
									double reduceVar$sum$10 = 0.0;
									for(int cv$reduction481Index = 0; cv$reduction481Index < noProducts; cv$reduction481Index += 1) {
										double i$var31 = reduceVar$sum$10;
										double j = exped[cv$reduction481Index];
										reduceVar$sum$10 = (i$var31 + j);
									}
									sum = reduceVar$sum$10;
								}
							}
						}
					}
				}
			}
			{
				boolean[] guard$sample19put43 = guard$sample19put43$global;
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						if(((0 <= i$var23) && (i$var23 < noProducts))) {
							{
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1)
									guard$sample19put43[((i$var38 - 0) / 1)] = false;
							}
						}
					}
				}
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var23 == i$var38))
								guard$sample19put43[((i$var38 - 0) / 1)] = false;
						}
					}
				}
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						if(((0 <= i$var23) && (i$var23 < noProducts))) {
							{
								for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
									if(!guard$sample19put43[((i$var38 - 0) / 1)]) {
										guard$sample19put43[((i$var38 - 0) / 1)] = true;
										{
											prob[i$var38] = (exped[i$var38] / sum);
										}
									}
								}
							}
						}
					}
				}
				for(int i$var23 = 0; i$var23 < noProducts; i$var23 += 1) {
					if((i$var12 == i$var23)) {
						for(int i$var38 = 0; i$var38 < noProducts; i$var38 += 1) {
							if((i$var23 == i$var38)) {
								if(!guard$sample19put43[((i$var38 - 0) / 1)]) {
									guard$sample19put43[((i$var38 - 0) / 1)] = true;
									{
										prob[i$var38] = (exped[i$var38] / sum);
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
		int cv$max_i$var38 = 0;
		cv$max_i$var38 = Math.max(cv$max_i$var38, ((noProducts - 0) / 1));
		guard$sample19put43$global = new boolean[cv$max_i$var38];
	}

	@Override
	public final void allocator() {
		if(!setFlag$ut) {
			{
				ut = new double[noProducts];
			}
		}
		if(!setFlag$exped) {
			{
				exped = new double[noProducts];
			}
		}
		if(!setFlag$prob) {
			{
				prob = new double[noProducts];
			}
		}
		if(!setFlag$choices) {
			{
				choices = new int[noObs];
			}
		}
		{
			logProbability$var17 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample19 = new double[((((noProducts - 1) - 1) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var12, int forEnd$i$var12, int threadID$i$var12, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var12 = forStart$i$var12; i$var12 < forEnd$i$var12; i$var12 += 1) {
						if(!fixedFlag$sample19)
							ut[i$var12] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(!fixedFlag$sample19)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		double reduceVar$sum$11 = 0.0;
		for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
			double i$var31 = reduceVar$sum$11;
			double j = exped[cv$reduction32Index];
			if(!fixedFlag$sample19)
				reduceVar$sum$11 = (i$var31 + j);
		}
		if(!fixedFlag$sample19)
			sum = reduceVar$sum$11;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample19)
							prob[i$var38] = (exped[i$var38] / sum);
					}
			}
		);
		parallelFor(RNG$, 0, noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!fixedFlag$sample49)
							choices[var46] = DistributionSampling.sampleCategorical(RNG$1, prob);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputs() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var12, int forEnd$i$var12, int threadID$i$var12, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var12 = forStart$i$var12; i$var12 < forEnd$i$var12; i$var12 += 1) {
						if(!fixedFlag$sample19)
							ut[i$var12] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(!fixedFlag$sample19)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		double reduceVar$sum$13 = 0.0;
		for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
			double i$var31 = reduceVar$sum$13;
			double j = exped[cv$reduction32Index];
			if(!fixedFlag$sample19)
				reduceVar$sum$13 = (i$var31 + j);
		}
		if(!fixedFlag$sample19)
			sum = reduceVar$sum$13;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample19)
							prob[i$var38] = (exped[i$var38] / sum);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var12, int forEnd$i$var12, int threadID$i$var12, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var12 = forStart$i$var12; i$var12 < forEnd$i$var12; i$var12 += 1) {
						if(!fixedFlag$sample19)
							ut[i$var12] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(!fixedFlag$sample19)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		double reduceVar$sum$12 = 0.0;
		for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
			double i$var31 = reduceVar$sum$12;
			double j = exped[cv$reduction32Index];
			if(!fixedFlag$sample19)
				reduceVar$sum$12 = (i$var31 + j);
		}
		if(!fixedFlag$sample19)
			sum = reduceVar$sum$12;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample19)
							prob[i$var38] = (exped[i$var38] / sum);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1) {
				if(!fixedFlag$sample19)
					sample19(i$var12);
			}
		} else {
			for(int i$var12 = (noProducts - ((((noProducts - 1) - 1) % 1) + 1)); i$var12 >= ((1 - 1) + 1); i$var12 -= 1) {
				if(!fixedFlag$sample19)
					sample19(i$var12);
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
		for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
			logProbability$var17[((i$var12 - 1) / 1)] = 0.0;
		logProbability$sum = 0.0;
		logProbability$ut = 0.0;
		logProbability$prob = 0.0;
		logProbability$exped = 0.0;
		if(!fixedProbFlag$sample19) {
			for(int i$var12 = 1; i$var12 < noProducts; i$var12 += 1)
				logProbability$sample19[((i$var12 - 1) / 1)] = 0.0;
		}
		logProbability$var42 = 0.0;
		logProbability$choices = 0.0;
		if(!fixedProbFlag$sample49)
			logProbability$var47 = 0.0;
	}

	@Override
	public final void logEvidenceGeneration() {
		forwardGenerationValuesNoOutputs();
		logEvidenceProbabilities();
	}

	private final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample19)
			logProbabilityValue$sample19();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample49();
	}

	@Override
	public final void logProbabilityGeneration() {
		parallelFor(RNG$, 1, noProducts, 1,
			(int forStart$i$var12, int forEnd$i$var12, int threadID$i$var12, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var12 = forStart$i$var12; i$var12 < forEnd$i$var12; i$var12 += 1) {
						if(!fixedFlag$sample19)
							ut[i$var12] = ((Math.sqrt(10.0) * DistributionSampling.sampleGaussian(RNG$1)) + 0.0);
					}
			}
		);
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(!fixedFlag$sample19)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		double reduceVar$sum$14 = 0.0;
		for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
			double i$var31 = reduceVar$sum$14;
			double j = exped[cv$reduction32Index];
			if(!fixedFlag$sample19)
				reduceVar$sum$14 = (i$var31 + j);
		}
		if(!fixedFlag$sample19)
			sum = reduceVar$sum$14;
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(!fixedFlag$sample19)
							prob[i$var38] = (exped[i$var38] / sum);
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
			(int forStart$i$var23, int forEnd$i$var23, int threadID$i$var23, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var23 = forStart$i$var23; i$var23 < forEnd$i$var23; i$var23 += 1) {
						if(setFlag$ut)
							exped[i$var23] = Math.exp(ut[i$var23]);
					}
			}
		);
		if(setFlag$ut) {
			double reduceVar$sum$15 = 0.0;
			for(int cv$reduction32Index = 0; cv$reduction32Index < noProducts; cv$reduction32Index += 1) {
				double i$var31 = reduceVar$sum$15;
				double j = exped[cv$reduction32Index];
				reduceVar$sum$15 = (i$var31 + j);
			}
			sum = reduceVar$sum$15;
		}
		parallelFor(RNG$, 0, noProducts, 1,
			(int forStart$i$var38, int forEnd$i$var38, int threadID$i$var38, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var38 = forStart$i$var38; i$var38 < forEnd$i$var38; i$var38 += 1) {
						if(setFlag$ut)
							prob[i$var38] = (exped[i$var38] / sum);
					}
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n * Sandwood\n *\n * Copyright (c) 2019-2023, Oracle and/or its affiliates\n * \n * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n */\n\npackage org.sandwood.compiler.tests.parser;\n\npublic model DiscreteChoice(int noProducts, int noObs, int[] ObsChoices) {\n    // we just need an uninformative prior for utility intercepts\n\n    // draw utilities\n    double[] ut = new double[noProducts];\n    ut[0] = 0.0;\n    for(int i=1; i<noProducts; i++) {\n        ut[i]= gaussian(0, 10).sample();\n    }\n\n    // calculate choice probabilities\n    double[] exped = new double[noProducts];\n    for(int i : [0..noProducts)) {\n        exped[i] = exp(ut[i]);\n    }\n    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n    double[] prob = new double[noProducts];\n    for (int i : [0..noProducts)) {\n        prob[i] = exped[i] / sum;\n    }\n    // draw consumer choices according to the calculated probabilities\n    int[] choices = categorical(prob).sample(noObs);\n\n    // assert generated choices match observed choices\n    choices.observe(ObsChoices);\n}";
	}
}