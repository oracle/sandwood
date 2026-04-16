package org.sandwood.compiler.tests.parser;

import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart5$SingleThreadCPU extends CoreModelSingleThreadCPU implements HMMTestPart5$CoreInterface {
double[] bias;
	boolean[] constrainedFlag$sample28;
	boolean[] constrainedFlag$sample45;
	boolean constrainedFlag$sample53 = true;
	boolean[] constrainedFlag$sample72;
	boolean fixedFlag$sample28 = false;
	boolean fixedFlag$sample45 = false;
	boolean fixedFlag$sample53 = false;
	boolean fixedFlag$sample72 = false;
	boolean fixedProbFlag$sample28 = false;
	boolean fixedProbFlag$sample45 = false;
	boolean fixedProbFlag$sample53 = false;
	boolean fixedProbFlag$sample72 = false;
	boolean fixedProbFlag$sample89 = false;
	boolean[] flips;
	boolean[] flipsMeasured;
	int length$flipsMeasured;
	double logProbability$$evidence;
	double logProbability$$model;
	double logProbability$bias;
	double logProbability$flips;
	double logProbability$m;
	double[] logProbability$sample72;
	double[] logProbability$sample89;
	double logProbability$st;
	double logProbability$var28;
	double logProbability$var44;
	double logProbability$var52;
	double[][] m;
	int samples;
	int[] st;
	int states;
	boolean system$gibbsForward = true;
	double[] v;
	double[] cv$var28$countGlobal;
	double[] cv$var52$stateProbabilityGlobal;
	double[] cv$var71$stateProbabilityGlobal;

	public HMMTestPart5$SingleThreadCPU(ExecutionTarget target) {
		super(target);
	}

	@Override
	public final double[] get$bias() {
		return bias;
	}

	@Override
	public final void set$bias(double[] cv$value, boolean allocated$) {
		bias = cv$value;
		fixedProbFlag$sample45 = false;
		fixedProbFlag$sample89 = false;
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
		fixedProbFlag$sample53 = (fixedFlag$sample28 && fixedProbFlag$sample53);
		fixedProbFlag$sample72 = (fixedFlag$sample28 && fixedProbFlag$sample72);
	}

	@Override
	public final boolean get$fixedFlag$sample45() {
		return fixedFlag$sample45;
	}

	@Override
	public final void set$fixedFlag$sample45(boolean cv$value, boolean allocated$) {
		fixedFlag$sample45 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
				constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		}
		fixedProbFlag$sample45 = (fixedFlag$sample45 && fixedProbFlag$sample45);
		fixedProbFlag$sample89 = (fixedFlag$sample45 && fixedProbFlag$sample89);
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
		fixedProbFlag$sample72 = (fixedFlag$sample53 && fixedProbFlag$sample72);
		fixedProbFlag$sample89 = (fixedFlag$sample53 && fixedProbFlag$sample89);
	}

	@Override
	public final boolean get$fixedFlag$sample72() {
		return fixedFlag$sample72;
	}

	@Override
	public final void set$fixedFlag$sample72(boolean cv$value, boolean allocated$) {
		fixedFlag$sample72 = cv$value;
		if(allocated$) {
			for(int index$constrainedFlag$sample72$1 = 0; index$constrainedFlag$sample72$1 < constrainedFlag$sample72.length; index$constrainedFlag$sample72$1 += 1)
				constrainedFlag$sample72[index$constrainedFlag$sample72$1] = true;
		}
		fixedProbFlag$sample72 = (fixedFlag$sample72 && fixedProbFlag$sample72);
		fixedProbFlag$sample89 = (fixedFlag$sample72 && fixedProbFlag$sample89);
	}

	@Override
	public final boolean[] get$flips() {
		return flips;
	}

	@Override
	public final boolean[] get$flipsMeasured() {
		return flipsMeasured;
	}

	@Override
	public final void set$flipsMeasured(boolean[] cv$value, boolean allocated$) {
		flipsMeasured = cv$value;
	}

	@Override
	public final int get$length$flipsMeasured() {
		return length$flipsMeasured;
	}

	@Override
	public final void set$length$flipsMeasured(int cv$value, boolean allocated$) {
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
	public final double get$logProbability$bias() {
		return logProbability$bias;
	}

	@Override
	public final double get$logProbability$flips() {
		return logProbability$flips;
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
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample72 = false;
	}

	@Override
	public final int get$samples() {
		return samples;
	}

	@Override
	public final int[] get$st() {
		return st;
	}

	@Override
	public final void set$st(int[] cv$value, boolean allocated$) {
		st = cv$value;
		fixedProbFlag$sample53 = false;
		fixedProbFlag$sample72 = false;
		fixedProbFlag$sample89 = false;
	}

	@Override
	public final int get$states() {
		return states;
	}

	@Override
	public final double[] get$v() {
		return v;
	}

	private final void drawValueSample28(int var27) {
		double[] var28 = m[var27];
		DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
	}

	private final void drawValueSample45(int var43) {
		bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample53() {
		st[0] = (states * DistributionSampling.sampleCategorical(RNG$, m[0], states));
	}

	private final void drawValueSample72(int i$var65) {
		st[i$var65] = (states * DistributionSampling.sampleCategorical(RNG$, m[st[(i$var65 - 1)]], states));
	}

	private final void inferSample28(int var27) {
		if(true) {
			constrainedFlag$sample28[((var27 - 0) / 1)] = false;
			double[] cv$targetLocal = m[var27];
			double[] cv$countLocal = cv$var28$countGlobal;
			int cv$arrayLength = states;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							if((var27 == 0)) {
								{
									{
										boolean cv$sampleConstrained = (fixedFlag$sample53 || constrainedFlag$sample53);
										if(cv$sampleConstrained) {
											constrainedFlag$sample28[((var27 - 0) / 1)] = true;
											{
												{
													{
														{
															{
																cv$countLocal[(st[0] / states)] = (cv$countLocal[(st[0] / states)] + 1.0);
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
				{
					{
						{
							for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
								if((var27 == st[(i$var65 - 1)])) {
									{
										{
											boolean cv$sampleConstrained = (fixedFlag$sample72 || constrainedFlag$sample72[((i$var65 - 1) / 1)]);
											if(cv$sampleConstrained) {
												constrainedFlag$sample28[((var27 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	cv$countLocal[(st[i$var65] / states)] = (cv$countLocal[(st[i$var65] / states)] + 1.0);
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
				Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$countLocal, cv$targetLocal, states);
		}
	}

	private final void inferSample45(int var43) {
		if(true) {
			constrainedFlag$sample45[((var43 - 0) / 1)] = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int j = 0; j < samples; j += 1) {
								if((var43 == st[j])) {
									{
										{
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												constrainedFlag$sample45[((var43 - 0) / 1)] = true;
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
			if(constrainedFlag$sample45[((var43 - 0) / 1)]) {
				double var44 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							bias[var43] = var44;
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
				cv$numStates = Math.max(cv$numStates, states);
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
							st[0] = (states * cv$currentValue);
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var50 = m[0];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < states)) && (0 < states)) && (0.0 <= var50[cv$currentValue])) && (var50[cv$currentValue] <= 1.0))?Math.log(var50[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var68$2_1 = (states * cv$currentValue);
								for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
									if((0 == (i$var65 - 1))) {
										{
											{
												boolean cv$sampleConstrained = (fixedFlag$sample72 || constrainedFlag$sample72[((i$var65 - 1) / 1)]);
												if(cv$sampleConstrained) {
													constrainedFlag$sample53 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double[] var69 = m[traceTempVariable$var68$2_1];
																		if(((Math.log(1.0) + ((((((0.0 <= (st[i$var65] / states)) && ((st[i$var65] / states) < states)) && (0 < states)) && (0.0 <= var69[(st[i$var65] / states)])) && (var69[(st[i$var65] / states)] <= 1.0))?Math.log(var69[(st[i$var65] / states)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (st[i$var65] / states)) && ((st[i$var65] / states) < states)) && (0 < states)) && (0.0 <= var69[(st[i$var65] / states)])) && (var69[(st[i$var65] / states)] <= 1.0))?Math.log(var69[(st[i$var65] / states)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (st[i$var65] / states)) && ((st[i$var65] / states) < states)) && (0 < states)) && (0.0 <= var69[(st[i$var65] / states)])) && (var69[(st[i$var65] / states)] <= 1.0))?Math.log(var69[(st[i$var65] / states)]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (st[i$var65] / states)) && ((st[i$var65] / states) < states)) && (0 < states)) && (0.0 <= var69[(st[i$var65] / states)])) && (var69[(st[i$var65] / states)] <= 1.0))?Math.log(var69[(st[i$var65] / states)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (st[i$var65] / states)) && ((st[i$var65] / states) < states)) && (0 < states)) && (0.0 <= var69[(st[i$var65] / states)])) && (var69[(st[i$var65] / states)] <= 1.0))?Math.log(var69[(st[i$var65] / states)]):Double.NEGATIVE_INFINITY)));
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
								int traceTempVariable$var85$5_1 = (states * cv$currentValue);
								for(int j = 0; j < samples; j += 1) {
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
																		double var86 = bias[traceTempVariable$var85$5_1];
																		if(((Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY)));
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
							st[0] = (states * var52);
						}
					}
				}
			}
		}
	}

	private final void inferSample72(int i$var65) {
		if(true) {
			constrainedFlag$sample72[((i$var65 - 1) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, states);
			}
			double[] cv$stateProbabilityLocal = cv$var71$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var71 = cv$currentValue;
				{
					{
						{
							st[i$var65] = (states * cv$currentValue);
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var69 = m[st[(i$var65 - 1)]];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < states)) && (0 < states)) && (0.0 <= var69[cv$currentValue])) && (var69[cv$currentValue] <= 1.0))?Math.log(var69[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var68$2_1 = (states * cv$currentValue);
								for(int index$i$2_2 = 1; index$i$2_2 < samples; index$i$2_2 += 1) {
									if((i$var65 == (index$i$2_2 - 1))) {
										{
											{
												boolean cv$sampleConstrained = (fixedFlag$sample72 || constrainedFlag$sample72[((index$i$2_2 - 1) / 1)]);
												if(cv$sampleConstrained) {
													constrainedFlag$sample72[((i$var65 - 1) / 1)] = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double[] sc$var69$1 = m[traceTempVariable$var68$2_1];
																		if(((Math.log(1.0) + ((((((0.0 <= (st[index$i$2_2] / states)) && ((st[index$i$2_2] / states) < states)) && (0 < states)) && (0.0 <= sc$var69$1[(st[index$i$2_2] / states)])) && (sc$var69$1[(st[index$i$2_2] / states)] <= 1.0))?Math.log(sc$var69$1[(st[index$i$2_2] / states)]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= (st[index$i$2_2] / states)) && ((st[index$i$2_2] / states) < states)) && (0 < states)) && (0.0 <= sc$var69$1[(st[index$i$2_2] / states)])) && (sc$var69$1[(st[index$i$2_2] / states)] <= 1.0))?Math.log(sc$var69$1[(st[index$i$2_2] / states)]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= (st[index$i$2_2] / states)) && ((st[index$i$2_2] / states) < states)) && (0 < states)) && (0.0 <= sc$var69$1[(st[index$i$2_2] / states)])) && (sc$var69$1[(st[index$i$2_2] / states)] <= 1.0))?Math.log(sc$var69$1[(st[index$i$2_2] / states)]):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= (st[index$i$2_2] / states)) && ((st[index$i$2_2] / states) < states)) && (0 < states)) && (0.0 <= sc$var69$1[(st[index$i$2_2] / states)])) && (sc$var69$1[(st[index$i$2_2] / states)] <= 1.0))?Math.log(sc$var69$1[(st[index$i$2_2] / states)]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= (st[index$i$2_2] / states)) && ((st[index$i$2_2] / states) < states)) && (0 < states)) && (0.0 <= sc$var69$1[(st[index$i$2_2] / states)])) && (sc$var69$1[(st[index$i$2_2] / states)] <= 1.0))?Math.log(sc$var69$1[(st[index$i$2_2] / states)]):Double.NEGATIVE_INFINITY)));
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
								int traceTempVariable$var85$5_1 = (states * cv$currentValue);
								for(int j = 0; j < samples; j += 1) {
									if((i$var65 == j)) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													constrainedFlag$sample72[((i$var65 - 1) / 1)] = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														{
															{
																{
																	{
																		double var86 = bias[traceTempVariable$var85$5_1];
																		if(((Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																		else {
																			if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																				cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY));
																			else
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((flips[j]?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY)));
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
			if(constrainedFlag$sample72[((i$var65 - 1) / 1)]) {
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
				int var71 = DistributionSampling.sampleCategorical(RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							st[i$var65] = (states * var71);
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
			for(int var27 = 0; var27 < states; var27 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = m[var27];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, v, states));
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
			for(int var27 = 0; var27 < states; var27 += 1)
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

	private final void logProbabilityValue$sample45() {
		if(!fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var43 = 0; var43 < states; var43 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = bias[var43];
						{
							{
								double var30 = 1.0;
								double var31 = 1.0;
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityBeta(cv$sampleValue, var30, var31));
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
			logProbability$var44 = cv$sampleAccumulator;
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample45 = fixedFlag$sample45;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var43 = 0; var43 < states; var43 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			logProbability$bias = (logProbability$bias + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample45)
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
					int cv$sampleValue = (st[0] / states);
					{
						{
							double[] var50 = m[0];
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < states)) && (0 < states)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			fixedProbFlag$sample53 = (fixedFlag$sample53 && fixedFlag$sample28);
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

	private final void logProbabilityValue$sample72() {
		if(!fixedProbFlag$sample72) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = (st[i$var65] / states);
						{
							{
								double[] var69 = m[st[(i$var65 - 1)]];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < states)) && (0 < states)) && (0.0 <= var69[cv$sampleValue])) && (var69[cv$sampleValue] <= 1.0))?Math.log(var69[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				logProbability$sample72[((i$var65 - 1) / 1)] = cv$sampleProbability;
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample72)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample72 = ((fixedFlag$sample72 && fixedFlag$sample28) && fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample72[((i$var65 - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			logProbability$st = (logProbability$st + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			if(fixedFlag$sample72)
				logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample89() {
		if(!fixedProbFlag$sample89) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < samples; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = flips[j];
						{
							{
								double var86 = bias[st[j]];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var86) && (var86 <= 1.0))?Math.log((cv$sampleValue?var86:(1.0 - var86))):Double.NEGATIVE_INFINITY));
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
				logProbability$sample89[((j - 0) / 1)] = cv$sampleProbability;
			}
			logProbability$flips = (logProbability$flips + cv$accumulator);
			logProbability$$model = (logProbability$$model + cv$accumulator);
			logProbability$$evidence = (logProbability$$evidence + cv$accumulator);
			fixedProbFlag$sample89 = ((fixedFlag$sample45 && fixedFlag$sample53) && fixedFlag$sample72);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < samples; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = logProbability$sample89[((j - 0) / 1)];
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
	public final void allocate() {
		{
			v = new double[2];
		}
		if(!fixedFlag$sample28) {
			{
				m = new double[2][];
				for(int var27 = 0; var27 < 2; var27 += 1)
					m[var27] = new double[2];
			}
		}
		if(!fixedFlag$sample45) {
			{
				bias = new double[2];
			}
		}
		if((!fixedFlag$sample53 || !fixedFlag$sample72)) {
			{
				st = new int[length$flipsMeasured];
			}
		}
		{
			flips = new boolean[length$flipsMeasured];
		}
		{
			constrainedFlag$sample45 = new boolean[((((2 - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample28 = new boolean[((((2 - 1) - 0) / 1) + 1)];
		}
		{
			constrainedFlag$sample72 = new boolean[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample72 = new double[((((length$flipsMeasured - 1) - 1) / 1) + 1)];
		}
		{
			logProbability$sample89 = new double[((((length$flipsMeasured - 1) - 0) / 1) + 1)];
		}
		allocateScratch();
	}

	@Override
	public final void allocateScratch() {
		{
			cv$var28$countGlobal = new double[2];
		}
		{
			int cv$var29$max = 2;
			cv$var52$stateProbabilityGlobal = new double[cv$var29$max];
		}
		{
			int cv$var29$max = 2;
			cv$var71$stateProbabilityGlobal = new double[cv$var29$max];
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = (states * DistributionSampling.sampleCategorical(RNG$, m[0], states));
		for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
			if(!fixedFlag$sample72)
				st[i$var65] = (states * DistributionSampling.sampleCategorical(RNG$, m[st[(i$var65 - 1)]], states));
		}
		for(int j = 0; j < samples; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = (states * DistributionSampling.sampleCategorical(RNG$, m[0], states));
		for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
			if(!fixedFlag$sample72)
				st[i$var65] = (states * DistributionSampling.sampleCategorical(RNG$, m[st[(i$var65 - 1)]], states));
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = (states * DistributionSampling.sampleCategorical(RNG$, m[0], states));
		for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
			if(!fixedFlag$sample72)
				st[i$var65] = (states * DistributionSampling.sampleCategorical(RNG$, m[st[(i$var65 - 1)]], states));
		}
		for(int j = 0; j < samples; j += 1)
			flips[j] = DistributionSampling.sampleBernoulli(RNG$, bias[st[j]]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = (states * DistributionSampling.sampleCategorical(RNG$, m[0], states));
		for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
			if(!fixedFlag$sample72)
				st[i$var65] = (states * DistributionSampling.sampleCategorical(RNG$, m[st[(i$var65 - 1)]], states));
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var27 = 0; var27 < states; var27 += 1) {
			double[] var28 = m[var27];
			if(!fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(RNG$, v, states, var28);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!fixedFlag$sample45)
				bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
		}
		if(!fixedFlag$sample53)
			st[0] = (states * DistributionSampling.sampleCategorical(RNG$, m[0], states));
		for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
			if(!fixedFlag$sample72)
				st[i$var65] = (states * DistributionSampling.sampleCategorical(RNG$, m[st[(i$var65 - 1)]], states));
		}
	}

	@Override
	public final void gibbsRound() {
		if(system$gibbsForward) {
			for(int var27 = 0; var27 < states; var27 += 1) {
				if(!fixedFlag$sample28)
					inferSample28(var27);
			}
			for(int var43 = 0; var43 < states; var43 += 1) {
				if(!fixedFlag$sample45)
					inferSample45(var43);
			}
			if(!fixedFlag$sample53)
				inferSample53();
			for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
				if(!fixedFlag$sample72)
					inferSample72(i$var65);
			}
		} else {
			for(int i$var65 = (samples - ((((samples - 1) - 1) % 1) + 1)); i$var65 >= ((1 - 1) + 1); i$var65 -= 1) {
				if(!fixedFlag$sample72)
					inferSample72(i$var65);
			}
			if(!fixedFlag$sample53)
				inferSample53();
			for(int var43 = (states - ((((states - 1) - 0) % 1) + 1)); var43 >= ((0 - 1) + 1); var43 -= 1) {
				if(!fixedFlag$sample45)
					inferSample45(var43);
			}
			for(int var27 = (states - ((((states - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
				if(!fixedFlag$sample28)
					inferSample28(var27);
			}
		}
		system$gibbsForward = !system$gibbsForward;
		for(int var27 = 0; var27 < states; var27 += 1) {
			if(!constrainedFlag$sample28[((var27 - 0) / 1)])
				drawValueSample28(var27);
		}
		for(int var43 = 0; var43 < states; var43 += 1) {
			if(!constrainedFlag$sample45[((var43 - 0) / 1)])
				drawValueSample45(var43);
		}
		if(!constrainedFlag$sample53)
			drawValueSample53();
		for(int i$var65 = 1; i$var65 < samples; i$var65 += 1) {
			if(!constrainedFlag$sample72[((i$var65 - 1) / 1)])
				drawValueSample72(i$var65);
		}
	}

	private final void initializeLogProbabilityFields() {
		logProbability$$model = 0.0;
		logProbability$$evidence = 0.0;
		logProbability$m = 0.0;
		if(!fixedProbFlag$sample28)
			logProbability$var28 = Double.NaN;
		logProbability$bias = 0.0;
		if(!fixedProbFlag$sample45)
			logProbability$var44 = Double.NaN;
		logProbability$st = 0.0;
		if(!fixedProbFlag$sample53)
			logProbability$var52 = Double.NaN;
		if(!fixedProbFlag$sample72) {
			for(int i$var65 = 1; i$var65 < samples; i$var65 += 1)
				logProbability$sample72[((i$var65 - 1) / 1)] = Double.NaN;
		}
		logProbability$flips = 0.0;
		if(!fixedProbFlag$sample89) {
			for(int j = 0; j < samples; j += 1)
				logProbability$sample89[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		states = 2;
		for(int i$var13 = 0; i$var13 < 2; i$var13 += 1)
			v[i$var13] = 0.1;
		samples = length$flipsMeasured;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample72$1 = 0; index$constrainedFlag$sample72$1 < constrainedFlag$sample72.length; index$constrainedFlag$sample72$1 += 1)
			constrainedFlag$sample72[index$constrainedFlag$sample72$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(fixedFlag$sample72)
			logProbabilityValue$sample72();
		logProbabilityValue$sample89();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample72();
		logProbabilityValue$sample89();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample72();
		logProbabilityValue$sample89();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = flipsMeasured;
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
		     + "model HMMTestPart5(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "        \n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = states * categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = states * categorical(m[st[i-1]]).sample();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}