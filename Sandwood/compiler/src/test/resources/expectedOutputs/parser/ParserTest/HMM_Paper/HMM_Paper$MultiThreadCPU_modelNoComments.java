package org.sandwood.compiler.tests.parser;

import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Paper$MultiThreadCPU extends org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU implements HMM_Paper$CoreInterface {
	private double[] bias;
	private boolean[] constrainedFlag$sample28;
	private boolean constrainedFlag$sample32 = true;
	private boolean[] constrainedFlag$sample47;
	private boolean constrainedFlag$sample53 = true;
	private boolean[] constrainedFlag$sample71;
	private double[][] cv$var28$countGlobal;
	private double[] cv$var31$countGlobal;
	private double[] cv$var52$stateProbabilityGlobal;
	private double[] cv$var70$stateProbabilityGlobal;
	private boolean fixedFlag$sample28 = false;
	private boolean fixedFlag$sample32 = false;
	private boolean fixedFlag$sample47 = false;
	private boolean fixedFlag$sample53 = false;
	private boolean fixedFlag$sample71 = false;
	private boolean fixedProbFlag$sample28 = false;
	private boolean fixedProbFlag$sample32 = false;
	private boolean fixedProbFlag$sample47 = false;
	private boolean fixedProbFlag$sample53 = false;
	private boolean fixedProbFlag$sample71 = false;
	private boolean fixedProbFlag$sample87 = false;
	private boolean[] flips;
	private double[] initialCoin;
	private int length$measured;
	private double logProbability$$evidence;
	private double logProbability$$model;
	private double logProbability$bias;
	private double logProbability$flips;
	private double logProbability$initialCoin;
	private double logProbability$m;
	private double[] logProbability$sample71;
	private double[] logProbability$sample87;
	private double logProbability$st;
	private double logProbability$var28;
	private double logProbability$var46;
	private double logProbability$var52;
	private double[][] m;
	private boolean[] measured;
	private int nCoins;
	private int nFlips;
	private int[] st;
	private boolean system$gibbsForward = true;
	private double[] v;

	public HMM_Paper$MultiThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample47 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final boolean get$fixedFlag$sample28() {
		return fixedFlag$sample28;
	}

	@Override
	public final void set$fixedFlag$sample28(boolean cv$value, boolean allocated$) {
		fixedFlag$sample28 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
				constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		}
		fixedProbFlag$sample28 = (fixedFlag$sample28 && fixedProbFlag$sample28);
		fixedProbFlag$sample71 = (fixedFlag$sample28 && fixedProbFlag$sample71);
	}

	@Override
	public final boolean get$fixedFlag$sample32() {
		return fixedFlag$sample32;
	}

	@Override
	public final void set$fixedFlag$sample32(boolean cv$value, boolean allocated$) {
		fixedFlag$sample32 = cv$value;
		constrainedFlag$sample32 = (fixedFlag$sample32 || constrainedFlag$sample32);
		fixedProbFlag$sample32 = (fixedFlag$sample32 && fixedProbFlag$sample32);
		fixedProbFlag$sample53 = (fixedFlag$sample32 && fixedProbFlag$sample53);
	}

	@Override
	public final boolean get$fixedFlag$sample47() {
		return fixedFlag$sample47;
	}

	@Override
	public final void set$fixedFlag$sample47(boolean cv$value, boolean allocated$) {
		fixedFlag$sample47 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
				constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		}
		fixedProbFlag$sample47 = (fixedFlag$sample47 && fixedProbFlag$sample47);
		fixedProbFlag$sample87 = (fixedFlag$sample47 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample53() {
		return fixedFlag$sample53;
	}

	@Override
	public final void set$fixedFlag$sample53(boolean cv$value, boolean allocated$) {
		fixedFlag$sample53 = cv$value;
		constrainedFlag$sample53 = (fixedFlag$sample53 || constrainedFlag$sample53);
		fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedProbFlag$sample53);
		fixedProbFlag$sample71 = (fixedFlag$sample53 && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (fixedFlag$sample53 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean get$fixedFlag$sample71() {
		return fixedFlag$sample71;
	}

	@Override
	public final void set$fixedFlag$sample71(boolean cv$value, boolean allocated$) {
		fixedFlag$sample71 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample71$1 = 0; index$constrainedFlag$sample71$1 < constrainedFlag$sample71.length; index$constrainedFlag$sample71$1 += 1)
				constrainedFlag$sample71[index$constrainedFlag$sample71$1] = true;
		}
		fixedProbFlag$sample71 = (fixedFlag$sample71 && fixedProbFlag$sample71);
		fixedProbFlag$sample87 = (fixedFlag$sample71 && fixedProbFlag$sample87);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final double[] get$initialCoin() {
		return initialCoin;
	}

	@Override
	public final void set$initialCoin(double[] cv$value, boolean allocated$) {
		initialCoin = cv$value;
		fixedProbFlag$sample32 = false;
		fixedProbFlag$sample53 = false;
	}

	@Override
	public final int get$length$measured() {
		return length$measured;
	}

	@Override
	public final void set$length$measured(int cv$value, boolean allocated$) {
		length$measured = cv$value;
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
	}

	@Override
	public final double get$logProbability$initialCoin() {
		return logProbability$initialCoin;
	}

	@Override
	public final double get$logProbability$m() {
		return logProbability$m;
	}

	@Override
	public final double get$logProbability$st() {
		return logProbability$st;
	}

	@Override
	public final double[][] get$m() {
		return m;
	}

	@Override
	public final void set$m(double[][] cv$value, boolean allocated$) {
		m = cv$value;
		fixedProbFlag$sample28 = false;
		fixedProbFlag$sample71 = false;
	}

	@Override
	public final boolean[] get$measured() {
		return measured;
	}

	@Override
	public final void set$measured(boolean[] cv$value, boolean allocated$) {
		measured = cv$value;
	}

	@Override
	public final int get$nCoins() {
		return nCoins;
	}

	@Override
	public final void set$nCoins(int cv$value, boolean allocated$) {
		nCoins = cv$value;
	}

	@Override
	public final int get$nFlips() {
		return nFlips;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value, boolean allocated$) {
		st = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample71 = false;
		fixedProbFlag$sample87 = false;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void drawValueSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		double[] var28 = m[var27];
		DistributionSampling.sampleDirichlet(RNG$, v, nCoins, var28);
	}

	private final void drawValueSample32() {
		DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
	}

	private final void drawValueSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample53() {
		st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
	}

	private final void drawValueSample71(int i) {
		st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
	}

	private final void inferSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		if(true) {
			constrainedFlag$sample28[((var27 - 0) / 1)] = false;
			double[] cv$targetLocal = m[var27];
			double[] cv$countLocal = cv$var28$countGlobal[threadID$cv$var27];
			int cv$arrayLength = nCoins;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int i = 1; i < nFlips; i += 1) {
								if((var27 == st[(i - 1)])) {
									{
										{
											boolean cv$sampleConstrained = (fixedFlag$sample71 || constrainedFlag$sample71[((i - 1) / 1)]);
											if(cv$sampleConstrained) {
												constrainedFlag$sample28[((var27 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	cv$countLocal[st[i]] = (cv$countLocal[st[i]] + 1.0);
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
				}
			}
			if(constrainedFlag$sample28[((var27 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, nCoins);
		}
	}

	private final void inferSample32() {
		if(true) {
			constrainedFlag$sample32 = false;
			double[] cv$targetLocal = initialCoin;
			double[] cv$countLocal = cv$var31$countGlobal;
			int cv$arrayLength = nCoins;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							{
								{
									boolean cv$sampleConstrained = (fixedFlag$sample53 || constrainedFlag$sample53);
									if(cv$sampleConstrained) {
										constrainedFlag$sample32 = true;
										{
											{
												{
													{
														{
															cv$countLocal[st[0]] = (cv$countLocal[st[0]] + 1.0);
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
			if(constrainedFlag$sample32)
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, nCoins);
		}
	}

	private final void inferSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		if(true) {
			constrainedFlag$sample47[((var45 - 0) / 1)] = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int j = 0; j < nFlips; j += 1) {
								if((var45 == st[j])) {
									{
										{
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												constrainedFlag$sample47[((var45 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	cv$count = (cv$count + 1);
																	if(flips[j])
																		cv$sum = (cv$sum + 1);
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
				}
			}
			if(constrainedFlag$sample47[((var45 - 0) / 1)]) {
				double var46 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							bias[var45] = var46;
						}
					}
				}
			}
		}
	}

	private final void inferSample53() {
		if(true) {
			constrainedFlag$sample53 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, nCoins);
			}
			double[] cv$stateProbabilityLocal = cv$var52$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var52 = cv$currentValue;
				{
					{
						{
							st[0] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < nCoins)) && (0 < nCoins)) && (0.0 <= initialCoin[cv$currentValue])) && (initialCoin[cv$currentValue] <= 1.0))?Math.log(initialCoin[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var67$2_1 = cv$currentValue;
								for(int i = 1; i < nFlips; i += 1) {
									if((0 == (i - 1))) {
										{
											{
												boolean cv$sampleConstrained = (fixedFlag$sample71 || constrainedFlag$sample71[((i - 1) / 1)]);
												if(cv$sampleConstrained) {
													constrainedFlag$sample53 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double[] var68 = m[traceTempVariable$var67$2_1];
																		if(((Math.log(1.0) + ((((((0.0 <= st[i]) && (st[i] < nCoins)) && (0 < nCoins)) && (0.0 <= var68[st[i]])) && (var68[st[i]] <= 1.0))?Math.log(var68[st[i]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= st[i]) && (st[i] < nCoins)) && (0 < nCoins)) && (0.0 <= var68[st[i]])) && (var68[st[i]] <= 1.0))?Math.log(var68[st[i]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= st[i]) && (st[i] < nCoins)) && (0 < nCoins)) && (0.0 <= var68[st[i]])) && (var68[st[i]] <= 1.0))?Math.log(var68[st[i]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= st[i]) && (st[i] < nCoins)) && (0 < nCoins)) && (0.0 <= var68[st[i]])) && (var68[st[i]] <= 1.0))?Math.log(var68[st[i]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= st[i]) && (st[i] < nCoins)) && (0 < nCoins)) && (0.0 <= var68[st[i]])) && (var68[st[i]] <= 1.0))?Math.log(var68[st[i]]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							{
								int traceTempVariable$var83$5_1 = cv$currentValue;
								for(int j = 0; j < nFlips; j += 1) {
									if((0 == j)) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													constrainedFlag$sample53 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double var84 = bias[traceTempVariable$var83$5_1];
																		if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
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
			if(constrainedFlag$sample53) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				int var52 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							st[0] = var52;
						}
					}
				}
			}
		}
	}

	private final void inferSample71(int i) {
		if(true) {
			constrainedFlag$sample71[((i - 1) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, nCoins);
			}
			double[] cv$stateProbabilityLocal = cv$var70$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var70 = cv$currentValue;
				{
					{
						{
							st[i] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var68 = m[st[(i - 1)]];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < nCoins)) && (0 < nCoins)) && (0.0 <= var68[cv$currentValue])) && (var68[cv$currentValue] <= 1.0))?Math.log(var68[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var67$2_1 = cv$currentValue;
								for(int index$i$2_2 = 1; index$i$2_2 < nFlips; index$i$2_2 += 1) {
									if((i == (index$i$2_2 - 1))) {
										{
											{
												boolean cv$sampleConstrained = (fixedFlag$sample71 || constrainedFlag$sample71[((index$i$2_2 - 1) / 1)]);
												if(cv$sampleConstrained) {
													constrainedFlag$sample71[((i - 1) / 1)] = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double[] sc$var68$1 = m[traceTempVariable$var67$2_1];
																		if(((Math.log(1.0) + ((((((0.0 <= st[index$i$2_2]) && (st[index$i$2_2] < nCoins)) && (0 < nCoins)) && (0.0 <= sc$var68$1[st[index$i$2_2]])) && (sc$var68$1[st[index$i$2_2]] <= 1.0))?Math.log(sc$var68$1[st[index$i$2_2]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= st[index$i$2_2]) && (st[index$i$2_2] < nCoins)) && (0 < nCoins)) && (0.0 <= sc$var68$1[st[index$i$2_2]])) && (sc$var68$1[st[index$i$2_2]] <= 1.0))?Math.log(sc$var68$1[st[index$i$2_2]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= st[index$i$2_2]) && (st[index$i$2_2] < nCoins)) && (0 < nCoins)) && (0.0 <= sc$var68$1[st[index$i$2_2]])) && (sc$var68$1[st[index$i$2_2]] <= 1.0))?Math.log(sc$var68$1[st[index$i$2_2]]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= st[index$i$2_2]) && (st[index$i$2_2] < nCoins)) && (0 < nCoins)) && (0.0 <= sc$var68$1[st[index$i$2_2]])) && (sc$var68$1[st[index$i$2_2]] <= 1.0))?Math.log(sc$var68$1[st[index$i$2_2]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= st[index$i$2_2]) && (st[index$i$2_2] < nCoins)) && (0 < nCoins)) && (0.0 <= sc$var68$1[st[index$i$2_2]])) && (sc$var68$1[st[index$i$2_2]] <= 1.0))?Math.log(sc$var68$1[st[index$i$2_2]]):Double.NEGATIVE_INFINITY)));
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
					{
						{
							{
								int traceTempVariable$var83$5_1 = cv$currentValue;
								for(int j = 0; j < nFlips; j += 1) {
									if((i == j)) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													constrainedFlag$sample71[((i - 1) / 1)] = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double var84 = bias[traceTempVariable$var83$5_1];
																		if(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY)));
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
			if(constrainedFlag$sample71[((i - 1) / 1)]) {
				double cv$logSum = 0.0;
				{
					double cv$lseMax = cv$stateProbabilityLocal[0];
					for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
						double cv$lseElementValue = cv$stateProbabilityLocal[cv$lseIndex];
						if((cv$lseMax < cv$lseElementValue))
							cv$lseMax = cv$lseElementValue;
					}
					if((cv$lseMax == Double.NEGATIVE_INFINITY))
						cv$logSum = Double.NEGATIVE_INFINITY;
					else {
						double cv$lseSum = 0.0;
						for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
							cv$lseSum = (cv$lseSum + Math.exp((cv$stateProbabilityLocal[cv$lseIndex] - cv$lseMax)));
						cv$logSum = (cv$logSum + (Math.log(cv$lseSum) + cv$lseMax));
					}
				}
				if((cv$logSum == Double.NEGATIVE_INFINITY)) {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$stateProbabilityLocal[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$stateProbabilityLocal[cv$indexName] = Double.NEGATIVE_INFINITY;
				int var70 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							st[i] = var70;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var27 = 0; var27 < nCoins; var27 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = m[var27];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, nCoins));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var28 = cv$sampleAccumulator;
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample28 = fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var27 = 0; var27 < nCoins; var27 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$m = (logProbability$m + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample28)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!fixedProbFlag$sample32) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = initialCoin;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, nCoins));
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
			logProbability$initialCoin = cv$sampleProbability;
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample32 = fixedFlag$sample32;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$initialCoin;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample32)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var45 = 0; var45 < nCoins; var45 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = bias[var45];
						{
							{
								double var32 = 1.0;
								double var33 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var32, var33));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
			}
			cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
			logProbability$var46 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample47 = fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var45 = 0; var45 < nCoins; var45 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample47)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!fixedProbFlag$sample53) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = st[0];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < nCoins)) && (0 < nCoins)) && (0.0 <= initialCoin[cv$sampleValue])) && (initialCoin[cv$sampleValue] <= 1.0))?Math.log(initialCoin[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			logProbability$var52 = cv$sampleProbability;
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample32);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = logProbability$var52;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample53)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample71() {
		if(!fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 1; i < nFlips; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = st[i];
						{
							{
								double[] var68 = m[st[(i - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < nCoins)) && (0 < nCoins)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample71[((i - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample71 = ((fixedFlag$sample71 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 1; i < nFlips; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample71[((i - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample71)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < nFlips; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = flips[j];
						{
							{
								double var84 = bias[st[j]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
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
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				logProbability$sample87[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample87 = ((fixedFlag$sample47 && fixedFlag$sample53) && fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < nFlips; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample87[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void allocateScratch() {
		{
			{
				int cv$threadCount = threadCount();
				cv$var28$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var28$countGlobal[cv$index] = new double[nCoins];
			}
		}
		{
			cv$var31$countGlobal = new double[nCoins];
		}
		{
			cv$var52$stateProbabilityGlobal = new double[nCoins];
		}
		{
			int cv$var29$max = nCoins;
			cv$var70$stateProbabilityGlobal = new double[cv$var29$max];
		}
	}

	@Override
	public final void allocator() {
		{
			v = new double[nCoins];
		}
		if(!fixedFlag$sample28) {
			{
				m = new double[nCoins][];
				for(int var27 = 0; var27 < nCoins; var27 += 1)
					m[var27] = new double[nCoins];
			}
		}
		if(!fixedFlag$sample32) {
			{
				initialCoin = new double[nCoins];
			}
		}
		if(!fixedFlag$sample47) {
			{
				bias = new double[nCoins];
			}
		}
		if((!fixedFlag$sample53 || !fixedFlag$sample71)) {
			{
				st = new int[length$measured];
			}
		}
		{
			flips = new boolean[length$measured];
		}
		{
			constrainedFlag$sample47 = new boolean[((((nCoins - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample28 = new boolean[((((nCoins - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample71 = new boolean[((((length$measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample71 = new double[((((length$measured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample87 = new double[((((length$measured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, nCoins, var28);
					}
			}
		);
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		for(int i = 1; i < nFlips; i += 1) {
			if(!fixedFlag$sample71)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
		parallelFor(RNG$, 0, nFlips, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, nCoins, var28);
					}
			}
		);
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		for(int i = 1; i < nFlips; i += 1) {
			if(!fixedFlag$sample71)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, nCoins, var28);
					}
			}
		);
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		for(int i = 1; i < nFlips; i += 1) {
			if(!fixedFlag$sample71)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
		parallelFor(RNG$, 0, nFlips, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						flips[j] = DistributionSampling.sampleBernoulli(RNG$1, bias[st[j]]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, nCoins, var28);
					}
			}
		);
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		for(int i = 1; i < nFlips; i += 1) {
			if(!fixedFlag$sample71)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						double[] var28 = m[var27];
						if(!fixedFlag$sample28)
							DistributionSampling.sampleDirichlet(RNG$1, v, nCoins, var28);
					}
			}
		);
		if(!fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(RNG$, v, nCoins, initialCoin);
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!fixedFlag$sample47)
							bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		if(!fixedFlag$sample53)
			st[0] = DistributionSampling.sampleCategorical(RNG$, initialCoin, nCoins);
		for(int i = 1; i < nFlips; i += 1) {
			if(!fixedFlag$sample71)
				st[i] = DistributionSampling.sampleCategorical(RNG$, m[st[(i - 1)]], nCoins);
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								inferSample28(var27, threadID$var27, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample32)
				inferSample32();
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								inferSample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample53)
				inferSample53();
			for(int i = 1; i < nFlips; i += 1) {
				if(!fixedFlag$sample71)
					inferSample71(i);
			}
		} else {
			for(int i = (nFlips - ((((nFlips - 1) - 1) % 1) + 1)); i >= ((1 - 1) + 1); i -= 1) {
				if(!fixedFlag$sample71)
					inferSample71(i);
			}
			if(!fixedFlag$sample53)
				inferSample53();
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!fixedFlag$sample47)
								inferSample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			if(!fixedFlag$sample32)
				inferSample32();
			parallelFor(RNG$, 0, nCoins, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
							if(!fixedFlag$sample28)
								inferSample28(var27, threadID$var27, RNG$1);
						}
				}
			);
		}
		system$gibbsForward = !system$gibbsForward;
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!constrainedFlag$sample28[((var27 - 0) / 1)])
							drawValueSample28(var27, threadID$var27, RNG$1);
					}
			}
		);
		if(!constrainedFlag$sample32)
			drawValueSample32();
		parallelFor(RNG$, 0, nCoins, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!constrainedFlag$sample47[((var45 - 0) / 1)])
							drawValueSample47(var45, threadID$var45, RNG$1);
					}
			}
		);
		if(!constrainedFlag$sample53)
			drawValueSample53();
		for(int i = 1; i < nFlips; i += 1) {
			if(!constrainedFlag$sample71[((i - 1) / 1)])
				drawValueSample71(i);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = Double.NaN;
		if(!fixedProbFlag$sample32)
			logProbability$initialCoin = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample47)
			logProbability$var46 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = Double.NaN;
		if(!fixedProbFlag$sample71) {
			for(int i = 1; i < nFlips; i += 1)
				logProbability$sample71[((i - 1) / 1)] = Double.NaN;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample87) {
			for(int j = 0; j < nFlips; j += 1)
				logProbability$sample87[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int var13 = 0; var13 < nCoins; var13 += 1)
			v[var13] = 0.1;
		nFlips = length$measured;
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample71$1 = 0; index$constrainedFlag$sample71$1 < constrainedFlag$sample71.length; index$constrainedFlag$sample71$1 += 1)
			constrainedFlag$sample71[index$constrainedFlag$sample71$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(fixedFlag$sample71)
			logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample32();
		logProbabilityValue$sample47();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample32();
		logProbabilityValue$sample47();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = measured;
		boolean[] cv$target1 = flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {}

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
		     + "model HMM_Paper(boolean[] measured, int nCoins) {\n"
		     + "  //Construct a transistion matrix m.\n"
		     + "  double[] v = new double[nCoins] <~ 0.1;\n"
		     + "  double[][] m = dirichlet(v).sample(nCoins);\n"
		     + "  \n"
		     + "  //Construct weighting for which coin to start at.\n"
		     + "  double[] initialCoin = dirichlet(v).sample;\n"
		     + "    \n"
		     + "  //Construct biases for each coin    \n"
		     + "  double[] bias = beta(1.0, 1.0).sample(nCoins);\n"
		     + "\n"
		     + "  //Allocate space to record which coin is flipped\n"
		     + "  int nFlips = measured.length;\n"
		     + "  int[] st = new int[nFlips];\n"
		     + "\n"
		     + "  //Calculate the movements between coins        \n"
		     + "  st[0] = categorical(initialCoin).sample();\n"
		     + "  for (int i: [1..nFlips) )\n"
		     + "    st[i] = categorical(m[st[i - 1]]).sample();\n"
		     + "\n"
		     + "  //Flip the coins\n"
		     + "  boolean[] flips = new boolean[nFlips];\n"
		     + "  for (int j: [0..nFlips) )\n"
		     + "    flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "    \n"
		     + "  //Assert that the flips match the measured data.\n"
		     + "  flips.observe(measured);\n"
		     + "}\n"
		     + "";
	}
}