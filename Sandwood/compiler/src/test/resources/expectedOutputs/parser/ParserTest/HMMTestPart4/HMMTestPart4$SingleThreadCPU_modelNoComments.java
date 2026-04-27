package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart4$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart4.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart4$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var121$stateProbabilityGlobal;
		double[] cv$var28$countGlobal;
		double[] cv$var81$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var28$countGlobal = new double[2];
			}
			{
				int cv$var29$max = 2;
				cv$var81$stateProbabilityGlobal = new double[cv$var29$max];
			}
			{
				int cv$var29$max = 2;
				cv$var121$stateProbabilityGlobal = new double[cv$var29$max];
			}
		}
	}


	public HMMTestPart4$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample124(int i1, int j1, int k1) {
		int[][] var116 = state.st[i1];
		int[] var117 = var116[j1];
		var117[k1] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
	}

	private final void drawValueSample28(int var27) {
		double[] var28 = state.m[var27];
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
	}

	private final void drawValueSample45(int var43) {
		state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample84() {
		int[][] var74 = state.st[0];
		int[] var76 = var74[0];
		var76[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
	}

	private final void inferSample124(int i1, int j1, int k1) {
		if(true) {
			state.constrainedFlag$sample124[((i1 - 1) / 1)][((j1 - 0) / 1)][((k1 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.states);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var121$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var121 = cv$currentValue;
				{
					{
						{
							int[][] var116 = state.st[i1];
							int[] var117 = var116[j1];
							var117[k1] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var119 = state.m[0];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var119[cv$currentValue])) && (var119[cv$currentValue] <= 1.0))?Math.log(var119[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var183$2_1 = cv$currentValue;
								for(int p = 0; p < state.samples; p += 1) {
									if((i1 == p)) {
										for(int l = 0; l < state.samples; l += 1) {
											if((j1 == l)) {
												for(int n = 0; n < state.samples; n += 1) {
													if((k1 == n)) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	state.constrainedFlag$sample124[((i1 - 1) / 1)][((j1 - 0) / 1)][((k1 - 0) / 1)] = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			{
																				{
																					{
																						double var184 = state.bias[traceTempVariable$var183$2_1];
																						if(((Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY)));
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
			if(state.constrainedFlag$sample124[((i1 - 1) / 1)][((j1 - 0) / 1)][((k1 - 0) / 1)]) {
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
				int var121 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							int[][] var116 = state.st[i1];
							int[] var117 = var116[j1];
							var117[k1] = var121;
						}
					}
				}
			}
		}
	}

	private final void inferSample28(int var27) {
		if(true) {
			state.constrainedFlag$sample28[((var27 - 0) / 1)] = false;
			double[] cv$targetLocal = state.m[var27];
			double[] cv$countLocal = scratch.cv$var28$countGlobal;
			int cv$arrayLength = state.states;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							if((var27 == 0)) {
								{
									{
										boolean cv$sampleConstrained = (state.fixedFlag$sample84 || state.constrainedFlag$sample84);
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
											{
												{
													{
														{
															{
																cv$countLocal[state.st[0][0][0]] = (cv$countLocal[state.st[0][0][0]] + 1.0);
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
							if((var27 == 0)) {
								for(int i1 = 1; i1 < state.samples; i1 += 1) {
									for(int j1 = 0; j1 < state.samples; j1 += 1) {
										for(int k1 = 0; k1 < state.samples; k1 += 1) {
											boolean cv$sampleConstrained = (state.fixedFlag$sample124 || state.constrainedFlag$sample124[((i1 - 1) / 1)][((j1 - 0) / 1)][((k1 - 0) / 1)]);
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample28[((var27 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	cv$countLocal[state.st[i1][j1][k1]] = (cv$countLocal[state.st[i1][j1][k1]] + 1.0);
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
			if(state.constrainedFlag$sample28[((var27 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, cv$countLocal, cv$targetLocal, state.states);
		}
	}

	private final void inferSample45(int var43) {
		if(true) {
			state.constrainedFlag$sample45[((var43 - 0) / 1)] = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							for(int l = 0; l < state.samples; l += 1) {
								for(int p = 0; p < state.samples; p += 1) {
									for(int n = 0; n < state.samples; n += 1) {
										if((var43 == state.st[p][l][n])) {
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														state.constrainedFlag$sample45[((var43 - 0) / 1)] = true;
														{
															{
																{
																	{
																		{
																			cv$count = (cv$count + 1);
																			if(state.flips[l][n][p])
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
				}
			}
			if(state.constrainedFlag$sample45[((var43 - 0) / 1)]) {
				double var44 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.bias[var43] = var44;
						}
					}
				}
			}
		}
	}

	private final void inferSample84() {
		if(true) {
			state.constrainedFlag$sample84 = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.states);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var81$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var81 = cv$currentValue;
				{
					{
						{
							int[][] var74 = state.st[0];
							int[] var76 = var74[0];
							var76[0] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var79 = state.m[0];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.states)) && (0 < state.states)) && (0.0 <= var79[cv$currentValue])) && (var79[cv$currentValue] <= 1.0))?Math.log(var79[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$var183$2_1 = cv$currentValue;
								for(int p = 0; p < state.samples; p += 1) {
									if((0 == p)) {
										for(int l = 0; l < state.samples; l += 1) {
											if((0 == l)) {
												for(int n = 0; n < state.samples; n += 1) {
													if((0 == n)) {
														{
															{
																boolean cv$sampleConstrained = true;
																if(cv$sampleConstrained) {
																	state.constrainedFlag$sample84 = true;
																	double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																	double cv$consumerDistributionProbabilityAccumulator = 1.0;
																	{
																		{
																			{
																				{
																					{
																						double var184 = state.bias[traceTempVariable$var183$2_1];
																						if(((Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																						else {
																							if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																								cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY));
																							else
																								cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((state.flips[l][n][p]?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY)));
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
			if(state.constrainedFlag$sample84) {
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
				int var81 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							int[][] var74 = state.st[0];
							int[] var76 = var74[0];
							var76[0] = var81;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample124() {
		if(!state.fixedProbFlag$sample124) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i1 = 1; i1 < state.samples; i1 += 1) {
				for(int j1 = 0; j1 < state.samples; j1 += 1) {
					for(int k1 = 0; k1 < state.samples; k1 += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							{
								int cv$sampleValue = state.st[i1][j1][k1];
								{
									{
										double[] var119 = state.m[0];
										double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
						state.logProbability$sample124[((i1 - 1) / 1)][((j1 - 0) / 1)][((k1 - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample124)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample124 = (state.fixedFlag$sample124 && state.fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i1 = 1; i1 < state.samples; i1 += 1) {
				for(int j1 = 0; j1 < state.samples; j1 += 1) {
					for(int k1 = 0; k1 < state.samples; k1 += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = state.logProbability$sample124[((i1 - 1) / 1)][((j1 - 0) / 1)][((k1 - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample124)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample191() {
		if(!state.fixedProbFlag$sample191) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1) {
						double cv$sampleAccumulator = 0.0;
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						{
							{
								boolean cv$sampleValue = state.flips[l][n][p];
								{
									{
										double var184 = state.bias[state.st[p][l][n]];
										double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var184) && (var184 <= 1.0))?Math.log((cv$sampleValue?var184:(1.0 - var184))):Double.NEGATIVE_INFINITY));
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
						state.logProbability$sample191[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = cv$sampleProbability;
					}
				}
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample191 = ((state.fixedFlag$sample45 && state.fixedFlag$sample84) && state.fixedFlag$sample124);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1) {
						double cv$rvAccumulator = 0.0;
						double cv$sampleValue = state.logProbability$sample191[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)];
						cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
						cv$sampleReached = true;
						cv$accumulator = (cv$accumulator + cv$rvAccumulator);
					}
				}
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var27 = 0; var27 < state.states; var27 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.m[var27];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.states));
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
			state.logProbability$var28 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var27 = 0; var27 < state.states; var27 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var28;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var43 = 0; var43 < state.states; var43 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.bias[var43];
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
			state.logProbability$var44 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample45 = state.fixedFlag$sample45;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var43 = 0; var43 < state.states; var43 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var44;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!state.fixedProbFlag$sample84) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					int cv$sampleValue = state.st[0][0][0];
					{
						{
							double[] var79 = state.m[0];
							double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.states)) && (0 < state.states)) && (0.0 <= var79[cv$sampleValue])) && (var79[cv$sampleValue] <= 1.0))?Math.log(var79[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
			state.logProbability$var81 = cv$sampleProbability;
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample84 = (state.fixedFlag$sample84 && state.fixedFlag$sample28);
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$var81;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		int[][] var74 = state.st[0];
		int[] var76 = var74[0];
		if(!state.fixedFlag$sample84)
			var76[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i1 = 1; i1 < state.samples; i1 += 1) {
			int[][] var116 = state.st[i1];
			for(int j1 = 0; j1 < state.samples; j1 += 1) {
				for(int k1 = 0; k1 < state.samples; k1 += 1) {
					int[] var117 = var116[j1];
					if(!state.fixedFlag$sample124)
						var117[k1] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
				}
			}
		}
		for(int l = 0; l < state.samples; l += 1) {
			boolean[][] var179 = state.flips[l];
			for(int p = 0; p < state.samples; p += 1) {
				for(int n = 0; n < state.samples; n += 1) {
					boolean[] var180 = var179[n];
					var180[p] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[p][l][n]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		int[][] var74 = state.st[0];
		int[] var76 = var74[0];
		if(!state.fixedFlag$sample84)
			var76[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i1 = 1; i1 < state.samples; i1 += 1) {
			int[][] var116 = state.st[i1];
			for(int j1 = 0; j1 < state.samples; j1 += 1) {
				for(int k1 = 0; k1 < state.samples; k1 += 1) {
					int[] var117 = var116[j1];
					if(!state.fixedFlag$sample124)
						var117[k1] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		int[][] var74 = state.st[0];
		int[] var76 = var74[0];
		if(!state.fixedFlag$sample84)
			var76[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i1 = 1; i1 < state.samples; i1 += 1) {
			int[][] var116 = state.st[i1];
			for(int j1 = 0; j1 < state.samples; j1 += 1) {
				for(int k1 = 0; k1 < state.samples; k1 += 1) {
					int[] var117 = var116[j1];
					if(!state.fixedFlag$sample124)
						var117[k1] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
				}
			}
		}
		for(int l = 0; l < state.samples; l += 1) {
			boolean[][] var179 = state.flips[l];
			for(int p = 0; p < state.samples; p += 1) {
				for(int n = 0; n < state.samples; n += 1) {
					boolean[] var180 = var179[n];
					var180[p] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[p][l][n]]);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		int[][] var74 = state.st[0];
		int[] var76 = var74[0];
		if(!state.fixedFlag$sample84)
			var76[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i1 = 1; i1 < state.samples; i1 += 1) {
			int[][] var116 = state.st[i1];
			for(int j1 = 0; j1 < state.samples; j1 += 1) {
				for(int k1 = 0; k1 < state.samples; k1 += 1) {
					int[] var117 = var116[j1];
					if(!state.fixedFlag$sample124)
						var117[k1] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			double[] var28 = state.m[var27];
			if(!state.fixedFlag$sample28)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.states, var28);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.fixedFlag$sample45)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		int[][] var74 = state.st[0];
		int[] var76 = var74[0];
		if(!state.fixedFlag$sample84)
			var76[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
		for(int i1 = 1; i1 < state.samples; i1 += 1) {
			int[][] var116 = state.st[i1];
			for(int j1 = 0; j1 < state.samples; j1 += 1) {
				for(int k1 = 0; k1 < state.samples; k1 += 1) {
					int[] var117 = var116[j1];
					if(!state.fixedFlag$sample124)
						var117[k1] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], state.states);
				}
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int var27 = 0; var27 < state.states; var27 += 1) {
				if(!state.fixedFlag$sample28)
					inferSample28(var27);
			}
			for(int var43 = 0; var43 < state.states; var43 += 1) {
				if(!state.fixedFlag$sample45)
					inferSample45(var43);
			}
			if(!state.fixedFlag$sample84)
				inferSample84();
			for(int i1 = 1; i1 < state.samples; i1 += 1) {
				for(int j1 = 0; j1 < state.samples; j1 += 1) {
					for(int k1 = 0; k1 < state.samples; k1 += 1) {
						if(!state.fixedFlag$sample124)
							inferSample124(i1, j1, k1);
					}
				}
			}
		} else {
			for(int i1 = (state.samples - ((((state.samples - 1) - 1) % 1) + 1)); i1 >= ((1 - 1) + 1); i1 -= 1) {
				for(int j1 = (state.samples - ((((state.samples - 1) - 0) % 1) + 1)); j1 >= ((0 - 1) + 1); j1 -= 1) {
					for(int k1 = (state.samples - ((((state.samples - 1) - 0) % 1) + 1)); k1 >= ((0 - 1) + 1); k1 -= 1) {
						if(!state.fixedFlag$sample124)
							inferSample124(i1, j1, k1);
					}
				}
			}
			if(!state.fixedFlag$sample84)
				inferSample84();
			for(int var43 = (state.states - ((((state.states - 1) - 0) % 1) + 1)); var43 >= ((0 - 1) + 1); var43 -= 1) {
				if(!state.fixedFlag$sample45)
					inferSample45(var43);
			}
			for(int var27 = (state.states - ((((state.states - 1) - 0) % 1) + 1)); var27 >= ((0 - 1) + 1); var27 -= 1) {
				if(!state.fixedFlag$sample28)
					inferSample28(var27);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var27 = 0; var27 < state.states; var27 += 1) {
			if(!state.constrainedFlag$sample28[((var27 - 0) / 1)])
				drawValueSample28(var27);
		}
		for(int var43 = 0; var43 < state.states; var43 += 1) {
			if(!state.constrainedFlag$sample45[((var43 - 0) / 1)])
				drawValueSample45(var43);
		}
		if(!state.constrainedFlag$sample84)
			drawValueSample84();
		for(int i1 = 1; i1 < state.samples; i1 += 1) {
			for(int j1 = 0; j1 < state.samples; j1 += 1) {
				for(int k1 = 0; k1 < state.samples; k1 += 1) {
					if(!state.constrainedFlag$sample124[((i1 - 1) / 1)][((j1 - 0) / 1)][((k1 - 0) / 1)])
						drawValueSample124(i1, j1, k1);
				}
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$var28 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample45)
			state.logProbability$var44 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample84)
			state.logProbability$var81 = Double.NaN;
		if(!state.fixedProbFlag$sample124) {
			for(int i1 = 1; i1 < state.samples; i1 += 1) {
				for(int j1 = 0; j1 < state.samples; j1 += 1) {
					for(int k1 = 0; k1 < state.samples; k1 += 1)
						state.logProbability$sample124[((i1 - 1) / 1)][((j1 - 0) / 1)][((k1 - 0) / 1)] = Double.NaN;
				}
			}
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample191) {
			for(int l = 0; l < state.samples; l += 1) {
				for(int p = 0; p < state.samples; p += 1) {
					for(int n = 0; n < state.samples; n += 1)
						state.logProbability$sample191[((l - 0) / 1)][((p - 0) / 1)][((n - 0) / 1)] = Double.NaN;
				}
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.states = 2;
		for(int i$var13 = 0; i$var13 < 2; i$var13 += 1)
			state.v[i$var13] = 0.1;
		state.samples = state.length$flipsMeasured.length;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample124$1 = 0; index$constrainedFlag$sample124$1 < state.constrainedFlag$sample124.length; index$constrainedFlag$sample124$1 += 1) {
			boolean[][] cv$constrainedFlag$sample124$1 = state.constrainedFlag$sample124[index$constrainedFlag$sample124$1];
			for(int index$constrainedFlag$sample124$2 = 0; index$constrainedFlag$sample124$2 < cv$constrainedFlag$sample124$1.length; index$constrainedFlag$sample124$2 += 1) {
				boolean[] cv$constrainedFlag$sample124$2 = cv$constrainedFlag$sample124$1[index$constrainedFlag$sample124$2];
				for(int index$constrainedFlag$sample124$3 = 0; index$constrainedFlag$sample124$3 < cv$constrainedFlag$sample124$2.length; index$constrainedFlag$sample124$3 += 1)
					cv$constrainedFlag$sample124$2[index$constrainedFlag$sample124$3] = true;
			}
		}
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(state.fixedFlag$sample84)
			logProbabilityValue$sample84();
		if(state.fixedFlag$sample124)
			logProbabilityValue$sample124();
		logProbabilityValue$sample191();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample84();
		logProbabilityValue$sample124();
		logProbabilityValue$sample191();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample84();
		logProbabilityValue$sample124();
		logProbabilityValue$sample191();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[][][] cv$source1 = state.flipsMeasured;
		boolean[][][] cv$target1 = state.flips;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[][] cv$source2 = cv$source1[cv$index1];
			boolean[][] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1) {
				boolean[] cv$source3 = cv$source2[cv$index2];
				boolean[] cv$target3 = cv$target2[cv$index2];
				int cv$length3 = cv$target3.length;
				for(int cv$index3 = 0; cv$index3 < cv$length3; cv$index3 += 1)
					cv$target3[cv$index3] = cv$source3[cv$index3];
			}
		}
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
		     + "model HMMTestPart4(boolean[][][] flipsMeasured) {\n"
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
		     + "        \n"
		     + "        \n"
		     + "        int[][][] st = new int[samples][][];\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i] = new int[samples][];\n"
		     + "            for(int j:[0..samples))\n"
		     + "                st[i][j] = new int[samples];\n"
		     + "        }\n"
		     + "\n"
		     + "        st[0][0][0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i1:[1..samples))\n"
		     + "            for(int j1:[0..samples))\n"
		     + "                for(int k1:[0..samples))\n"
		     + "                    st[i1][j1][k1] = categorical(m[0]).sample();\n"
		     + "            \n"
		     + "        boolean[][][] flips = new boolean[samples][][];\n"
		     + "        for(int i2:[0..samples)) {\n"
		     + "            flips[i2] = new boolean[samples][];\n"
		     + "            for(int j2:[0..samples))\n"
		     + "                flips[i2][j2] = new boolean[samples];\n"
		     + "        }\n"
		     + "            \n"
		     + "        for(int l:[0..samples))\n"
		     + "            for(int p:[0..samples))\n"
		     + "                for(int n:[0..samples))\n"
		     + "                    flips[l][n][p] = bernoulli(bias[st[p][l][n]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}