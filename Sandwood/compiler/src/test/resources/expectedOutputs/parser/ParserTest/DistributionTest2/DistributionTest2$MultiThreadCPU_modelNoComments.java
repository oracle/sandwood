package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DistributionTest2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DistributionTest2.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DistributionTest2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[][] cv$var23$stateProbabilityGlobal;
		double[] cv$var5$stateProbabilityGlobal;
		double[] cv$var9$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var5$stateProbabilityGlobal = new double[state.weightings.length];
			}
			{
				cv$var9$stateProbabilityGlobal = new double[state.weightings.length];
			}
			{
				{
					int cv$threadCount = threadCount();
					cv$var23$stateProbabilityGlobal = new double[cv$threadCount][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var23$stateProbabilityGlobal[cv$index] = new double[state.weightings.length];
				}
			}
		}
	}


	public DistributionTest2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample23(int i, int threadID$cv$i, Rng RNG$) {
		int index$i$1 = i;
		state.v2[i] = DistributionSampling.sampleCategorical(RNG$, state.weightings, state.weightings.length);
	}

	private final void drawValueSample5() {
		state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	private final void drawValueSample9() {
		state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
	}

	private final void inferSample23(int i, int threadID$cv$i, Rng RNG$) {
		int index$i$1 = i;
		if(true) {
			state.constrainedFlag$sample23[((i - 1) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.weightings.length);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var23$stateProbabilityGlobal[threadID$cv$i];
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					int $var425 = state.weightings.length;
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < $var425)) && (0 < $var425)) && (0.0 <= state.weightings[cv$currentValue])) && (state.weightings[cv$currentValue] <= 1.0))?Math.log(state.weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var38$2_1 = cv$currentValue;
								for(int j = 0; j < state.size; j += 1) {
									if((i == j)) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample23[((i - 1) / 1)] = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(state.fixedFlag$sample5) {
															{
																{
																	{
																		{
																			double var39 = ((1.0 * state.v1) / traceTempVariable$var38$2_1);
																			if(((Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample5$5 = 0; index$sample5$5 < state.weightings.length; index$sample5$5 += 1) {
																	int distributionTempVariable$v1$7 = index$sample5$5;
																	double cv$probabilitySample5Value6 = (1.0 * state.distribution$sample5[index$sample5$5]);
																	{
																		{
																			{
																				{
																					double var39 = ((1.0 * distributionTempVariable$v1$7) / traceTempVariable$var38$2_1);
																					if(((Math.log(cv$probabilitySample5Value6) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value6) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value6) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value6) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample5Value6) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value6);
																				}
																			}
																		}
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
			if(state.constrainedFlag$sample23[((i - 1) / 1)]) {
				double[] cv$localProbability = state.distribution$sample23[((i - 1) / 1)];
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
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	private final void inferSample5() {
		if(true) {
			state.constrainedFlag$sample5 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.weightings.length);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var5$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					int $var383 = state.weightings.length;
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < $var383)) && (0 < $var383)) && (0.0 <= state.weightings[cv$currentValue])) && (state.weightings[cv$currentValue] <= 1.0))?Math.log(state.weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								for(int j = 0; j < state.size; j += 1) {
									int traceTempVariable$v1$1_2 = cv$currentValue;
									{
										{
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample5 = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													if(state.fixedFlag$sample9) {
														{
															if((0 == j)) {
																{
																	{
																		{
																			double var39 = ((1.0 * traceTempVariable$v1$1_2) / state.v2[j]);
																			if(((Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														}
													} else {
														if(true) {
															for(int index$sample9$4 = 0; index$sample9$4 < state.weightings.length; index$sample9$4 += 1) {
																int distributionTempVariable$var9$6 = index$sample9$4;
																double cv$probabilitySample9Value5 = (1.0 * state.distribution$sample9[index$sample9$4]);
																{
																	int traceTempVariable$var38$7_1 = distributionTempVariable$var9$6;
																	if((0 == j)) {
																		{
																			{
																				{
																					double var39 = ((1.0 * traceTempVariable$v1$1_2) / traceTempVariable$var38$7_1);
																					if(((Math.log(cv$probabilitySample9Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample9Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample9Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample9Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample9Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample9Value5);
																				}
																			}
																		}
																	}
																}
															}
														}
													}
													if(state.fixedFlag$sample23) {
														{
															for(int i = 1; i < state.size; i += 1) {
																if((i == j)) {
																	{
																		{
																			{
																				double var39 = ((1.0 * traceTempVariable$v1$1_2) / state.v2[j]);
																				if(((Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																				else {
																					if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																						cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																					else
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)));
																				}
																				cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																			}
																		}
																	}
																}
															}
														}
													} else {
														for(int i = 1; i < state.size; i += 1) {
															if(true) {
																for(int index$sample23$10 = 0; index$sample23$10 < state.weightings.length; index$sample23$10 += 1) {
																	int distributionTempVariable$var23$12 = index$sample23$10;
																	double cv$probabilitySample23Value11 = (1.0 * state.distribution$sample23[((i - 1) / 1)][index$sample23$10]);
																	{
																		int traceTempVariable$var38$13_1 = distributionTempVariable$var23$12;
																		if((i == j)) {
																			{
																				{
																					{
																						double var39 = ((1.0 * traceTempVariable$v1$1_2) / traceTempVariable$var38$13_1);
																						if(((Math.log(cv$probabilitySample23Value11) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample23Value11) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample23Value11) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample23Value11) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample23Value11) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)));
																						}
																						cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample23Value11);
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
				cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue - Math.log(cv$reachedDistributionSourceRV)) + cv$accumulatedDistributionProbabilities);
			}
			if(state.constrainedFlag$sample5) {
				double[] cv$localProbability = state.distribution$sample5;
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
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	private final void inferSample9() {
		if(true) {
			state.constrainedFlag$sample9 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.weightings.length);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var9$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					int $var408 = state.weightings.length;
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < $var408)) && (0 < $var408)) && (0.0 <= state.weightings[cv$currentValue])) && (state.weightings[cv$currentValue] <= 1.0))?Math.log(state.weightings[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var38$1_1 = cv$currentValue;
								for(int j = 0; j < state.size; j += 1) {
									if((0 == j)) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample9 = true;
													double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
													double cv$consumerDistributionProbabilityAccumulator = 1.0;
													{
														if(state.fixedFlag$sample5) {
															{
																{
																	{
																		{
																			double var39 = ((1.0 * state.v1) / traceTempVariable$var38$1_1);
																			if(((Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)));
																			}
																			cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - 1.0);
																		}
																	}
																}
															}
														} else {
															if(true) {
																for(int index$sample5$4 = 0; index$sample5$4 < state.weightings.length; index$sample5$4 += 1) {
																	int distributionTempVariable$v1$6 = index$sample5$4;
																	double cv$probabilitySample5Value5 = (1.0 * state.distribution$sample5[index$sample5$4]);
																	{
																		{
																			{
																				{
																					double var39 = ((1.0 * distributionTempVariable$v1$6) / traceTempVariable$var38$1_1);
																					if(((Math.log(cv$probabilitySample5Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(cv$probabilitySample5Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample5Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(cv$probabilitySample5Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(cv$probabilitySample5Value5) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((state.v[j]?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY)));
																					}
																					cv$consumerDistributionProbabilityAccumulator = (cv$consumerDistributionProbabilityAccumulator - cv$probabilitySample5Value5);
																				}
																			}
																		}
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
			if(state.constrainedFlag$sample9) {
				double[] cv$localProbability = state.distribution$sample9;
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
						cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
				} else {
					for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
						cv$localProbability[cv$indexName] = Math.exp((cv$stateProbabilityLocal[cv$indexName] - cv$logSum));
				}
				for(int cv$indexName = cv$numStates; cv$indexName < cv$stateProbabilityLocal.length; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
			}
		}
	}

	private final void logProbabilityDistribution$sample23() {
		if(!state.fixedProbFlag$sample23) {
			if(state.fixedFlag$sample23) {
				double cv$accumulator = 0.0;
				boolean cv$sampleReached = false;
				for(int i = 1; i < state.size; i += 1) {
					double cv$sampleAccumulator = 0.0;
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int index$i$1 = i;
					{
						{
							int cv$sampleValue = state.v2[i];
							{
								{
									double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
					state.logProbability$sample23[((i - 1) / 1)] = cv$sampleProbability;
				}
				if(state.fixedFlag$sample23)
					state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample23)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample23 = state.fixedFlag$sample23;
			}
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 1; i < state.size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample23[((i - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			if(state.fixedFlag$sample23)
				state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample23)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample41() {
		if(!state.fixedProbFlag$sample41) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.v[j];
						if(state.fixedFlag$sample5) {
							{
								if(state.fixedFlag$sample9) {
									{
										if((0 == j)) {
											{
												double var39 = ((1.0 * state.v1) / state.v2[j]);
												double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((cv$sampleValue?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
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
								} else {
									if(true) {
										for(int index$sample9$8 = 0; index$sample9$8 < state.weightings.length; index$sample9$8 += 1) {
											int distributionTempVariable$var9$10 = index$sample9$8;
											double cv$probabilitySample9Value9 = (1.0 * state.distribution$sample9[index$sample9$8]);
											{
												int traceTempVariable$var38$11_1 = distributionTempVariable$var9$10;
												if((0 == j)) {
													{
														double var39 = ((1.0 * state.v1) / traceTempVariable$var38$11_1);
														double cv$weightedProbability = (Math.log(cv$probabilitySample9Value9) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((cv$sampleValue?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample9Value9);
													}
												}
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample5$3 = 0; index$sample5$3 < state.weightings.length; index$sample5$3 += 1) {
									int distributionTempVariable$v1$5 = index$sample5$3;
									double cv$probabilitySample5Value4 = (1.0 * state.distribution$sample5[index$sample5$3]);
									{
										if(state.fixedFlag$sample9) {
											{
												if((0 == j)) {
													{
														double var39 = ((1.0 * distributionTempVariable$v1$5) / state.v2[j]);
														double cv$weightedProbability = (Math.log(cv$probabilitySample5Value4) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((cv$sampleValue?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
														if((cv$weightedProbability < cv$distributionAccumulator))
															cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
														else {
															if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																cv$distributionAccumulator = cv$weightedProbability;
															else
																cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
														}
														cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value4);
													}
												}
											}
										} else {
											if(true) {
												for(int index$sample9$13 = 0; index$sample9$13 < state.weightings.length; index$sample9$13 += 1) {
													int distributionTempVariable$var9$15 = index$sample9$13;
													double cv$probabilitySample9Value14 = (cv$probabilitySample5Value4 * state.distribution$sample9[index$sample9$13]);
													{
														int traceTempVariable$var38$16_1 = distributionTempVariable$var9$15;
														if((0 == j)) {
															{
																double var39 = ((1.0 * distributionTempVariable$v1$5) / traceTempVariable$var38$16_1);
																double cv$weightedProbability = (Math.log(cv$probabilitySample9Value14) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((cv$sampleValue?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																if((cv$weightedProbability < cv$distributionAccumulator))
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																else {
																	if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																		cv$distributionAccumulator = cv$weightedProbability;
																	else
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																}
																cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample9Value14);
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
						if(state.fixedFlag$sample5) {
							{
								if(state.fixedFlag$sample23) {
									{
										for(int i = 1; i < state.size; i += 1) {
											if((i == j)) {
												{
													double var39 = ((1.0 * state.v1) / state.v2[j]);
													double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((cv$sampleValue?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
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
								} else {
									for(int i = 1; i < state.size; i += 1) {
										if(true) {
											for(int index$sample23$24 = 0; index$sample23$24 < state.weightings.length; index$sample23$24 += 1) {
												int distributionTempVariable$var23$26 = index$sample23$24;
												double cv$probabilitySample23Value25 = (1.0 * state.distribution$sample23[((i - 1) / 1)][index$sample23$24]);
												{
													int traceTempVariable$var38$27_1 = distributionTempVariable$var23$26;
													if((i == j)) {
														{
															double var39 = ((1.0 * state.v1) / traceTempVariable$var38$27_1);
															double cv$weightedProbability = (Math.log(cv$probabilitySample23Value25) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((cv$sampleValue?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample23Value25);
														}
													}
												}
											}
										}
									}
								}
							}
						} else {
							if(true) {
								for(int index$sample5$18 = 0; index$sample5$18 < state.weightings.length; index$sample5$18 += 1) {
									int distributionTempVariable$v1$20 = index$sample5$18;
									double cv$probabilitySample5Value19 = (1.0 * state.distribution$sample5[index$sample5$18]);
									{
										if(state.fixedFlag$sample23) {
											{
												for(int i = 1; i < state.size; i += 1) {
													if((i == j)) {
														{
															double var39 = ((1.0 * distributionTempVariable$v1$20) / state.v2[j]);
															double cv$weightedProbability = (Math.log(cv$probabilitySample5Value19) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((cv$sampleValue?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
															if((cv$weightedProbability < cv$distributionAccumulator))
																cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
															else {
																if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																	cv$distributionAccumulator = cv$weightedProbability;
																else
																	cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
															}
															cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample5Value19);
														}
													}
												}
											}
										} else {
											for(int i = 1; i < state.size; i += 1) {
												if(true) {
													for(int index$sample23$30 = 0; index$sample23$30 < state.weightings.length; index$sample23$30 += 1) {
														int distributionTempVariable$var23$32 = index$sample23$30;
														double cv$probabilitySample23Value31 = (cv$probabilitySample5Value19 * state.distribution$sample23[((i - 1) / 1)][index$sample23$30]);
														{
															int traceTempVariable$var38$33_1 = distributionTempVariable$var23$32;
															if((i == j)) {
																{
																	double var39 = ((1.0 * distributionTempVariable$v1$20) / traceTempVariable$var38$33_1);
																	double cv$weightedProbability = (Math.log(cv$probabilitySample23Value31) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((cv$sampleValue?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
																	if((cv$weightedProbability < cv$distributionAccumulator))
																		cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
																	else {
																		if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
																			cv$distributionAccumulator = cv$weightedProbability;
																		else
																			cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
																	}
																	cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample23Value31);
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
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				double cv$sampleProbability = cv$distributionAccumulator;
				cv$sampleReached = true;
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$sampleProbability);
				cv$accumulator = (cv$accumulator + cv$sampleAccumulator);
				state.logProbability$sample41[((j - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample41 = ((state.fixedFlag$sample5 && state.fixedFlag$sample9) && state.fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample41[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample5() {
		if(!state.fixedProbFlag$sample5) {
			if(state.fixedFlag$sample5) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.v1;
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$v1 = cv$sampleProbability;
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample5)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample5 = state.fixedFlag$sample5;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample9() {
		if(!state.fixedProbFlag$sample9) {
			if(state.fixedFlag$sample9) {
				double cv$accumulator = 0.0;
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.v2[0];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$var9 = cv$sampleProbability;
				if(state.fixedFlag$sample9)
					state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				if(state.fixedFlag$sample9)
					state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
			}
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample23() {
		if(!state.fixedProbFlag$sample23) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 1; i < state.size; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				int index$i$1 = i;
				{
					{
						int cv$sampleValue = state.v2[i];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample23[((i - 1) / 1)] = cv$sampleProbability;
			}
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample23)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample23 = state.fixedFlag$sample23;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 1; i < state.size; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample23[((i - 1) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample23)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample41() {
		if(!state.fixedProbFlag$sample41) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.size; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.v[j];
						{
							{
								double var39 = ((1.0 * state.v1) / state.v2[j]);
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var39) && (var39 <= 1.0))?Math.log((cv$sampleValue?var39:(1.0 - var39))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample41[((j - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample41 = ((state.fixedFlag$sample5 && state.fixedFlag$sample9) && state.fixedFlag$sample23);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j = 0; j < state.size; j += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample41[((j - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$v = (state.logProbability$v + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!state.fixedProbFlag$sample5) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.v1;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$v1 = cv$sampleProbability;
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample5 = state.fixedFlag$sample5;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$v1;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.v2[0];
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[cv$sampleValue])) && (state.weightings[cv$sampleValue] <= 1.0))?Math.log(state.weightings[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$var9 = cv$sampleProbability;
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample9;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var9;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$v2 = (state.logProbability$v2 + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample9)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample9)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		parallelFor(state.RNG$, 1, state.size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample23)
							state.v2[i] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((1.0 * state.v1) / state.v2[j]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		double[] cv$distribution$sample5 = state.distribution$sample5;
		for(int index$var4 = 0; index$var4 < state.weightings.length; index$var4 += 1) {
			double cv$value = ((((((0.0 <= index$var4) && (index$var4 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[index$var4])) && (state.weightings[index$var4] <= 1.0))?state.weightings[index$var4]:0.0);
			if(!state.fixedFlag$sample5)
				cv$distribution$sample5[index$var4] = cv$value;
		}
		double[] cv$distribution$sample9 = state.distribution$sample9;
		for(int index$var8 = 0; index$var8 < state.weightings.length; index$var8 += 1) {
			double cv$value = ((((((0.0 <= index$var8) && (index$var8 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[index$var8])) && (state.weightings[index$var8] <= 1.0))?state.weightings[index$var8]:0.0);
			if(!state.fixedFlag$sample9)
				cv$distribution$sample9[index$var8] = cv$value;
		}
		parallelFor(state.RNG$, 1, state.size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						double[] cv$distribution$sample23 = state.distribution$sample23[((i - 1) / 1)];
						for(int index$var22 = 0; index$var22 < state.weightings.length; index$var22 += 1) {
							double cv$value = ((((((0.0 <= index$var22) && (index$var22 < state.weightings.length)) && (0 < state.weightings.length)) && (0.0 <= state.weightings[index$var22])) && (state.weightings[index$var22] <= 1.0))?state.weightings[index$var22]:0.0);
							if(!state.fixedFlag$sample23)
								cv$distribution$sample23[index$var22] = cv$value;
						}
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample9)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		parallelFor(state.RNG$, 1, state.size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample23)
							state.v2[i] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.size, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.v[j] = DistributionSampling.sampleBernoulli(RNG$1, ((1.0 * state.v1) / state.v2[j]));
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample9)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		parallelFor(state.RNG$, 1, state.size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample23)
							state.v2[i] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.v1 = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		if(!state.fixedFlag$sample9)
			state.v2[0] = DistributionSampling.sampleCategorical(state.RNG$, state.weightings, state.weightings.length);
		parallelFor(state.RNG$, 1, state.size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample23)
							state.v2[i] = DistributionSampling.sampleCategorical(RNG$1, state.weightings, state.weightings.length);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample5)
				inferSample5();
			if(!state.fixedFlag$sample9)
				inferSample9();
			parallelFor(state.RNG$, 1, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!state.fixedFlag$sample23)
								inferSample23(i, threadID$i, RNG$1);
						}
				}
			);
		} else {
			parallelFor(state.RNG$, 1, state.size, 1,
				(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i = forStart$i; i < forEnd$i; i += 1) {
							if(!state.fixedFlag$sample23)
								inferSample23(i, threadID$i, RNG$1);
						}
				}
			);
			if(!state.fixedFlag$sample9)
				inferSample9();
			if(!state.fixedFlag$sample5)
				inferSample5();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample5)
			drawValueSample5();
		if(!state.constrainedFlag$sample9)
			drawValueSample9();
		parallelFor(state.RNG$, 1, state.size, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.constrainedFlag$sample23[((i - 1) / 1)])
							drawValueSample23(i, threadID$i, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample5)
			state.logProbability$v1 = Double.NaN;
		state.logProbability$v2 = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$var9 = Double.NaN;
		if(!state.fixedProbFlag$sample23) {
			for(int i = 1; i < state.size; i += 1)
				state.logProbability$sample23[((i - 1) / 1)] = Double.NaN;
		}
		state.logProbability$v = 0.0;
		if(!state.fixedProbFlag$sample41) {
			for(int j = 0; j < state.size; j += 1)
				state.logProbability$sample41[((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.size = state.length$value;
		for(int index$constrainedFlag$sample23$1 = 0; index$constrainedFlag$sample23$1 < state.constrainedFlag$sample23.length; index$constrainedFlag$sample23$1 += 1)
			state.constrainedFlag$sample23[index$constrainedFlag$sample23$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample41();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityDistribution$sample5();
		logProbabilityDistribution$sample9();
		logProbabilityDistribution$sample23();
		logProbabilityDistribution$sample41();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample9();
		logProbabilityValue$sample23();
		logProbabilityValue$sample41();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = state.value;
		boolean[] cv$target1 = state.v;
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
		     + "model DistributionTest2(double[] weightings, boolean[] value) {\n"
		     + "    int size = value.length;\n"
		     + "    \n"
		     + "    int v1 = categorical(weightings).sampleDistribution();\n"
		     + "    \n"
		     + "    int[] v2 = new int[size];\n"
		     + "    v2[0] = categorical(weightings).sampleDistribution();\n"
		     + "    for(int i:[1..size))\n"
		     + "        v2[i] = categorical(weightings).sampleDistribution();\n"
		     + "        \n"
		     + "    boolean[] v = new boolean[size];\n"
		     + "    for(int j:[0..size))\n"
		     + "        v[j] = bernoulli((1.0*v1)/v2[j]).sample();\n"
		     + "        \n"
		     + "    v.observe(value);\n"
		     + "}\n"
		     + "";
	}
}