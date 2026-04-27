package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ReductionTest$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ReductionTest.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ReductionTest$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[][] cv$var30$countGlobal;
		double[] cv$var61$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				{
					int cv$threadCount = threadCount();
					cv$var30$countGlobal = new double[cv$threadCount][];
					for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
						cv$var30$countGlobal[cv$index] = new double[((0.0 <= state.flipsMeasured.length)?((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):state.flipsMeasured.length)):((state.flipsMeasured.length < 0.0)?((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):(-state.flipsMeasured.length))):((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):Math.max(state.flipsMeasured.length, (-state.flipsMeasured.length))))))];
				}
			}
			{
				int cv$var31$max = ((0.0 <= state.flipsMeasured.length)?((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):state.flipsMeasured.length)):((state.flipsMeasured.length < 0.0)?((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):(-state.flipsMeasured.length))):((0.0 <= state.noCats)?(state.flipsMeasured.length / state.noCats):((state.noCats < 0.0)?(state.flipsMeasured.length / state.noCats):Math.max(state.flipsMeasured.length, (-state.flipsMeasured.length))))));
				cv$var61$stateProbabilityGlobal = new double[cv$var31$max];
			}
		}
	}


	public ReductionTest$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		double[] var30 = state.m[var29];
		DistributionSampling.sampleDirichlet(RNG$, state.v, state.noStates, var30);
	}

	private final void drawValueSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		state.bias[var45] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample62(int i$var58) {
		state.st[i$var58] = DistributionSampling.sampleCategorical(state.RNG$, state.m[i$var58], state.noStates);
	}

	private final void inferSample30(int var29, int threadID$cv$var29, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample30[((var29 - 0) / 1)] = false;
			double[] cv$targetLocal = state.m[var29];
			double[] cv$countLocal = scratch.cv$var30$countGlobal[threadID$cv$var29];
			int cv$arrayLength = state.noStates;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
								if((var29 == i$var58)) {
									{
										{
											boolean cv$sampleConstrained = (state.fixedFlag$sample62 || state.constrainedFlag$sample62[((i$var58 - 0) / 1)]);
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample30[((var29 - 0) / 1)] = true;
												{
													{
														{
															{
																{
																	cv$countLocal[state.st[i$var58]] = (cv$countLocal[state.st[i$var58]] + 1.0);
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
			if(state.constrainedFlag$sample30[((var29 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, cv$targetLocal, state.noStates);
		}
	}

	private final void inferSample47(int var45, int threadID$cv$var45, Rng RNG$) {
		if(true) {
			state.constrainedFlag$sample47[((var45 - 0) / 1)] = false;
			int cv$sum = 0;
			int cv$count = 0;
			{
				{
					{
						{
							int reduceVar$var82$5 = 0;
							for(int cv$reduction78Index = 0; cv$reduction78Index < state.noCats; cv$reduction78Index += 1) {
								int i$var79 = reduceVar$var82$5;
								int j$var80 = state.st[cv$reduction78Index];
								reduceVar$var82$5 = (i$var79 + j$var80);
							}
							if((var45 == reduceVar$var82$5)) {
								for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1) {
									boolean cv$sampleConstrained = true;
									if(cv$sampleConstrained) {
										state.constrainedFlag$sample47[((var45 - 0) / 1)] = true;
										{
											{
												{
													{
														{
															cv$count = (cv$count + 1);
															if(state.flips[j$var73])
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
			if(state.constrainedFlag$sample47[((var45 - 0) / 1)]) {
				double var46 = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
				{
					{
						{
							state.bias[var45] = var46;
						}
					}
				}
			}
		}
	}

	private final void inferSample62(int i$var58) {
		if(true) {
			state.constrainedFlag$sample62[((i$var58 - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.noStates);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var61$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				int var61 = cv$currentValue;
				{
					{
						{
							state.st[i$var58] = cv$currentValue;
						}
					}
				}
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var59 = state.m[i$var58];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var59[cv$currentValue])) && (var59[cv$currentValue] <= 1.0))?Math.log(var59[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								int traceTempVariable$i$2_1 = cv$currentValue;
								if(((0 <= i$var58) && (i$var58 < state.noCats))) {
									if((0 < state.noCats)) {
										int reduceVar$var82$6 = 0;
										for(int cv$reduction543Index = 0; cv$reduction543Index < i$var58; cv$reduction543Index += 1) {
											int i$var79 = reduceVar$var82$6;
											int j$var80 = state.st[cv$reduction543Index];
											reduceVar$var82$6 = (i$var79 + j$var80);
										}
										for(int cv$reduction543Index = (i$var58 + 1); cv$reduction543Index < state.noCats; cv$reduction543Index += 1) {
											int i$var79 = reduceVar$var82$6;
											int j$var80 = state.st[cv$reduction543Index];
											reduceVar$var82$6 = (i$var79 + j$var80);
										}
										int cv$reduced78 = reduceVar$var82$6;
										reduceVar$var82$6 = (traceTempVariable$i$2_1 + cv$reduced78);
										int traceTempVariable$var82$2_2 = reduceVar$var82$6;
										for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1) {
											boolean cv$sampleConstrained = true;
											if(cv$sampleConstrained) {
												state.constrainedFlag$sample62[((i$var58 - 0) / 1)] = true;
												double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
												double cv$consumerDistributionProbabilityAccumulator = 1.0;
												{
													{
														{
															{
																{
																	double var83 = state.bias[traceTempVariable$var82$2_2];
																	if(((Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																	else {
																		if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																			cv$accumulatedConsumerProbabilities = (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY));
																		else
																			cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((state.flips[j$var73]?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY)));
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
			if(state.constrainedFlag$sample62[((i$var58 - 0) / 1)]) {
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
				int var61 = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
				{
					{
						{
							state.st[i$var58] = var61;
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample30() {
		if(!state.fixedProbFlag$sample30) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var29 = 0; var29 < state.noCats; var29 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.m[var29];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, state.noStates));
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
			state.logProbability$var30 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample30)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample30 = state.fixedFlag$sample30;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var29 = 0; var29 < state.noCats; var29 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var30;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$m = (state.logProbability$m + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample30)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!state.fixedProbFlag$sample47) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var45 = 0; var45 < state.noFlips; var45 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.bias[var45];
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
			state.logProbability$var46 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var45 = 0; var45 < state.noFlips; var45 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var46;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$bias = (state.logProbability$bias + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample62() {
		if(!state.fixedProbFlag$sample62) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.st[i$var58];
						{
							{
								double[] var59 = state.m[i$var58];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var59[cv$sampleValue])) && (var59[cv$sampleValue] <= 1.0))?Math.log(var59[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample62[((i$var58 - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample62)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample62 = (state.fixedFlag$sample62 && state.fixedFlag$sample30);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample62[((i$var58 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample62)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!state.fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						boolean cv$sampleValue = state.flips[j$var73];
						{
							{
								int reduceVar$var82$7 = 0;
								for(int cv$reduction78Index = 0; cv$reduction78Index < state.noCats; cv$reduction78Index += 1) {
									int i$var79 = reduceVar$var82$7;
									int j$var80 = state.st[cv$reduction78Index];
									reduceVar$var82$7 = (i$var79 + j$var80);
								}
								double var83 = state.bias[reduceVar$var82$7];
								double cv$weightedProbability = (Math.log(1.0) + (((0.0 <= var83) && (var83 <= 1.0))?Math.log((cv$sampleValue?var83:(1.0 - var83))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample87[((j$var73 - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample87 = (state.fixedFlag$sample47 && state.fixedFlag$sample62);
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample87[((j$var73 - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$j$var73, int forEnd$j$var73, int threadID$j$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var73 = forStart$j$var73; j$var73 < forEnd$j$var73; j$var73 += 1) {
						int reduceVar$var82$8 = 0;
						for(int cv$reduction78Index = 0; cv$reduction78Index < state.noCats; cv$reduction78Index += 1) {
							int i$var79 = reduceVar$var82$8;
							int j$var80 = state.st[cv$reduction78Index];
							reduceVar$var82$8 = (i$var79 + j$var80);
						}
						state.flips[j$var73] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[reduceVar$var82$8]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$j$var73, int forEnd$j$var73, int threadID$j$var73, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j$var73 = forStart$j$var73; j$var73 < forEnd$j$var73; j$var73 += 1) {
						int reduceVar$var82$9 = 0;
						for(int cv$reduction78Index = 0; cv$reduction78Index < state.noCats; cv$reduction78Index += 1) {
							int i$var79 = reduceVar$var82$9;
							int j$var80 = state.st[cv$reduction78Index];
							reduceVar$var82$9 = (i$var79 + j$var80);
						}
						state.flips[j$var73] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[reduceVar$var82$9]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						double[] var30 = state.m[var29];
						if(!state.fixedFlag$sample30)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, var30);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.fixedFlag$sample47)
							state.bias[var45] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$i$var58, int forEnd$i$var58, int threadID$i$var58, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var58 = forStart$i$var58; i$var58 < forEnd$i$var58; i$var58 += 1) {
						if(!state.fixedFlag$sample62)
							state.st[i$var58] = DistributionSampling.sampleCategorical(RNG$1, state.m[i$var58], state.noStates);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			parallelFor(state.RNG$, 0, state.noCats, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!state.fixedFlag$sample30)
								inferSample30(var29, threadID$var29, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noFlips, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!state.fixedFlag$sample47)
								inferSample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
				if(!state.fixedFlag$sample62)
					inferSample62(i$var58);
			}
		} else {
			for(int i$var58 = (state.noCats - ((((state.noCats - 1) - 0) % 1) + 1)); i$var58 >= ((0 - 1) + 1); i$var58 -= 1) {
				if(!state.fixedFlag$sample62)
					inferSample62(i$var58);
			}
			parallelFor(state.RNG$, 0, state.noFlips, 1,
				(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
							if(!state.fixedFlag$sample47)
								inferSample47(var45, threadID$var45, RNG$1);
						}
				}
			);
			parallelFor(state.RNG$, 0, state.noCats, 1,
				(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
							if(!state.fixedFlag$sample30)
								inferSample30(var29, threadID$var29, RNG$1);
						}
				}
			);
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, state.noCats, 1,
			(int forStart$var29, int forEnd$var29, int threadID$var29, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var29 = forStart$var29; var29 < forEnd$var29; var29 += 1) {
						if(!state.constrainedFlag$sample30[((var29 - 0) / 1)])
							drawValueSample30(var29, threadID$var29, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noFlips, 1,
			(int forStart$var45, int forEnd$var45, int threadID$var45, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var45 = forStart$var45; var45 < forEnd$var45; var45 += 1) {
						if(!state.constrainedFlag$sample47[((var45 - 0) / 1)])
							drawValueSample47(var45, threadID$var45, RNG$1);
					}
			}
		);
		for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1) {
			if(!state.constrainedFlag$sample62[((i$var58 - 0) / 1)])
				drawValueSample62(i$var58);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample30)
			state.logProbability$var30 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample47)
			state.logProbability$var46 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample62) {
			for(int i$var58 = 0; i$var58 < state.noCats; i$var58 += 1)
				state.logProbability$sample62[((i$var58 - 0) / 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample87) {
			for(int j$var73 = 0; j$var73 < state.noFlips; j$var73 += 1)
				state.logProbability$sample87[((j$var73 - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.noFlips = state.length$flipsMeasured;
		state.noStates = (state.length$flipsMeasured / state.noCats);
		for(int i$var15 = 0; i$var15 < (state.length$flipsMeasured / state.noCats); i$var15 += 1)
			state.v[i$var15] = 0.1;
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < state.constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			state.constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		for(int index$constrainedFlag$sample30$1 = 0; index$constrainedFlag$sample30$1 < state.constrainedFlag$sample30.length; index$constrainedFlag$sample30$1 += 1)
			state.constrainedFlag$sample30[index$constrainedFlag$sample30$1] = true;
		for(int index$constrainedFlag$sample62$1 = 0; index$constrainedFlag$sample62$1 < state.constrainedFlag$sample62.length; index$constrainedFlag$sample62$1 += 1)
			state.constrainedFlag$sample62[index$constrainedFlag$sample62$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample30)
			logProbabilityValue$sample30();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(state.fixedFlag$sample62)
			logProbabilityValue$sample62();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample30();
		logProbabilityValue$sample47();
		logProbabilityValue$sample62();
		logProbabilityValue$sample87();
	}

	@Override
	public final void propagateObservedValues() {
		boolean[] cv$source1 = state.flipsMeasured;
		boolean[] cv$target1 = state.flips;
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
		     + "model ReductionTest(boolean[] flipsMeasured, int noCats) {\n"
		     + "    int noFlips = flipsMeasured.length;\n"
		     + "    int noStates = noFlips/noCats;\n"
		     + "    \n"
		     + "    double[] v = new double[noStates];\n"
		     + "    for(int i:[0..noStates))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(noCats);\n"
		     + "    \n"
		     + "    double[] bias = beta(1.0, 1.0).sample(noFlips);\n"
		     + "    \n"
		     + "    int[] st = new int[noCats];\n"
		     + "\n"
		     + "\n"
		     + "    for(int i:[0..noCats))\n"
		     + "        st[i] = categorical(m[i]).sample();\n"
		     + "            \n"
		     + "    boolean[] flips = new boolean[noFlips];\n"
		     + "            \n"
		     + "    for(int j:[0..noFlips))\n"
		     + "        flips[j] = bernoulli(bias[sum(st)]).sample();\n"
		     + "\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "    \n"
		     + "    private int sum(int[] a) {\n"
		     + "        return reduce(a, 0, (i,j) -> {\n"
		     + "            return i + j;\n"
		     + "        });\n"
		     + "    }\n"
		     + "}";
	}
}