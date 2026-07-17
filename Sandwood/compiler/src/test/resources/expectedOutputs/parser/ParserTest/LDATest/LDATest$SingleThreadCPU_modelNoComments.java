package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.LDATest$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.LDATest.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class LDATest$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var42$countGlobal;
		double[] cv$var57$countGlobal;
		double[] cv$var88$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				cv$var42$countGlobal = new double[state.vocabSize];
			}
			{
				cv$var57$countGlobal = new double[state.noTopics];
			}
			{
				int cv$var58$max = state.noTopics;
				cv$var88$stateProbabilityGlobal = new double[cv$var58$max];
			}
		}
	}


	public LDATest$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample42(int var41) {
		double[] var42 = state.phi[var41];
		DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, var42);
	}

	private final void drawValueSample58(int var56) {
		double[] var57 = state.theta[var56];
		DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, var57);
	}

	private final void drawValueSample90(int i$var71, int j) {
		state.z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
	}

	private final void inferSample42(int var41) {
		if(true) {
			state.constrainedFlag$sample42[((var41 - 0) / 1)] = false;
			double[] cv$targetLocal = state.phi[var41];
			double[] cv$countLocal = scratch.cv$var42$countGlobal;
			int cv$arrayLength = state.vocabSize;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
								for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
									if((var41 == state.z[((i$var71 - 0) / 1)][((j - 0) / 1)])) {
										{
											{
												boolean cv$sampleConstrained = true;
												if(cv$sampleConstrained) {
													state.constrainedFlag$sample42[((var41 - 0) / 1)] = true;
													{
														{
															{
																{
																	{
																		cv$countLocal[state.w[i$var71][j]] = (cv$countLocal[state.w[i$var71][j]] + 1.0);
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
			if(state.constrainedFlag$sample42[((var41 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.beta, cv$countLocal, cv$targetLocal, state.vocabSize);
		}
	}

	private final void inferSample58(int var56) {
		if(true) {
			state.constrainedFlag$sample58[((var56 - 0) / 1)] = false;
			double[] cv$targetLocal = state.theta[var56];
			double[] cv$countLocal = scratch.cv$var57$countGlobal;
			int cv$arrayLength = state.noTopics;
			for(int cv$loopIndex = 0; cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
				cv$countLocal[cv$loopIndex] = 0.0;
			{
				{
					{
						{
							for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
								if((var56 == i$var71)) {
									for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
										boolean cv$sampleConstrained = state.constrainedFlag$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)];
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample58[((var56 - 0) / 1)] = true;
											{
												{
													{
														{
															{
																cv$countLocal[state.z[((i$var71 - 0) / 1)][((j - 0) / 1)]] = (cv$countLocal[state.z[((i$var71 - 0) / 1)][((j - 0) / 1)]] + 1.0);
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
			if(state.constrainedFlag$sample58[((var56 - 0) / 1)])
				Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.alpha, cv$countLocal, cv$targetLocal, state.noTopics);
		}
	}

	private final void inferSample90(int i$var71, int j) {
		if(true) {
			state.constrainedFlag$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)] = false;
			int cv$numStates = 0;
			{
				cv$numStates = Math.max(cv$numStates, state.noTopics);
			}
			double[] cv$stateProbabilityLocal = scratch.cv$var88$stateProbabilityGlobal;
			for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
				double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
				double cv$reachedDistributionSourceRV = 0.0;
				double cv$accumulatedDistributionProbabilities = 0.0;
				int cv$currentValue;
				cv$currentValue = cv$valuePos;
				state.z[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$currentValue;
				{
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
					double[] var86 = state.theta[i$var71];
					double cv$accumulatedProbabilities = (Math.log(1.0) + ((((((0.0 <= cv$currentValue) && (cv$currentValue < state.noTopics)) && (0 < state.noTopics)) && (0.0 <= var86[cv$currentValue])) && (var86[cv$currentValue] <= 1.0))?Math.log(var86[cv$currentValue]):Double.NEGATIVE_INFINITY));
					{
						{
							{
								{
									{
										boolean cv$sampleConstrained = true;
										if(cv$sampleConstrained) {
											state.constrainedFlag$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)] = true;
											double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
											double cv$consumerDistributionProbabilityAccumulator = 1.0;
											{
												{
													{
														{
															{
																double[] var89 = state.phi[cv$currentValue];
																if(((Math.log(1.0) + ((((((0.0 <= state.w[i$var71][j]) && (state.w[i$var71][j] < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[state.w[i$var71][j]])) && (var89[state.w[i$var71][j]] <= 1.0))?Math.log(var89[state.w[i$var71][j]]):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																	cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((((((0.0 <= state.w[i$var71][j]) && (state.w[i$var71][j] < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[state.w[i$var71][j]])) && (var89[state.w[i$var71][j]] <= 1.0))?Math.log(var89[state.w[i$var71][j]]):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																else {
																	if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																		cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((((((0.0 <= state.w[i$var71][j]) && (state.w[i$var71][j] < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[state.w[i$var71][j]])) && (var89[state.w[i$var71][j]] <= 1.0))?Math.log(var89[state.w[i$var71][j]]):Double.NEGATIVE_INFINITY));
																	else
																		cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((((((0.0 <= state.w[i$var71][j]) && (state.w[i$var71][j] < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[state.w[i$var71][j]])) && (var89[state.w[i$var71][j]] <= 1.0))?Math.log(var89[state.w[i$var71][j]]):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((((((0.0 <= state.w[i$var71][j]) && (state.w[i$var71][j] < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[state.w[i$var71][j]])) && (var89[state.w[i$var71][j]] <= 1.0))?Math.log(var89[state.w[i$var71][j]]):Double.NEGATIVE_INFINITY)));
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
			if(state.constrainedFlag$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)]) {
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
				state.z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(state.RNG$, cv$stateProbabilityLocal, cv$numStates);
			}
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!state.fixedProbFlag$sample42) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.phi[var41];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.beta, state.vocabSize));
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
			state.logProbability$var42 = cv$sampleAccumulator;
			state.logProbability$phi = (state.logProbability$phi + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample42 = state.fixedFlag$sample42;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var41 = 0; var41 < state.noTopics; var41 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var42;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$phi = (state.logProbability$phi + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample58() {
		if(!state.fixedProbFlag$sample58) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double[] cv$sampleValue = state.theta[var56];
						{
							{
								double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.alpha, state.noTopics));
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
			state.logProbability$var57 = cv$sampleAccumulator;
			state.logProbability$theta = (state.logProbability$theta + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample58)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample58 = state.fixedFlag$sample58;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1)
				cv$sampleReached = true;
			double cv$sampleValue = state.logProbability$var57;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			state.logProbability$theta = (state.logProbability$theta + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample58)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample90() {
		double cv$accumulator = 0.0;
		boolean cv$sampleReached = false;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.z[((i$var71 - 0) / 1)][((j - 0) / 1)];
						{
							{
								double[] var86 = state.theta[i$var71];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noTopics)) && (0 < state.noTopics)) && (0.0 <= var86[cv$sampleValue])) && (var86[cv$sampleValue] <= 1.0))?Math.log(var86[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
			}
		}
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
	}

	private final void logProbabilityValue$sample93() {
		double cv$accumulator = 0.0;
		boolean cv$sampleReached = false;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						int cv$sampleValue = state.w[i$var71][j];
						{
							{
								double[] var89 = state.phi[state.z[((i$var71 - 0) / 1)][((j - 0) / 1)]];
								double cv$weightedProbability = (Math.log(1.0) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.vocabSize)) && (0 < state.vocabSize)) && (0.0 <= var89[cv$sampleValue])) && (var89[cv$sampleValue] <= 1.0))?Math.log(var89[cv$sampleValue]):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample93[((i$var71 - 0) / 1)][((j - 0) / 1)] = cv$sampleProbability;
			}
		}
		state.logProbability$w = (state.logProbability$w + cv$accumulator);
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
		state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
	}

	@Override
	public final void forwardGeneration() {
		for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
			double[] var42 = state.phi[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, var42);
		}
		for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
			double[] var57 = state.theta[var56];
			if(!state.fixedFlag$sample58)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, var57);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			int[] t = state.w[i$var71];
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				state.z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
				t[j] = DistributionSampling.sampleCategorical(state.RNG$, state.phi[state.z[((i$var71 - 0) / 1)][((j - 0) / 1)]], state.vocabSize);
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
			double[] var42 = state.phi[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, var42);
		}
		for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
			double[] var57 = state.theta[var56];
			if(!state.fixedFlag$sample58)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, var57);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
			double[] var42 = state.phi[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, var42);
		}
		for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
			double[] var57 = state.theta[var56];
			if(!state.fixedFlag$sample58)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, var57);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			int[] t = state.w[i$var71];
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				state.z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
				t[j] = DistributionSampling.sampleCategorical(state.RNG$, state.phi[state.z[((i$var71 - 0) / 1)][((j - 0) / 1)]], state.vocabSize);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
			double[] var42 = state.phi[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, var42);
		}
		for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
			double[] var57 = state.theta[var56];
			if(!state.fixedFlag$sample58)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, var57);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
			double[] var42 = state.phi[var41];
			if(!state.fixedFlag$sample42)
				DistributionSampling.sampleDirichlet(state.RNG$, state.beta, state.vocabSize, var42);
		}
		for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
			double[] var57 = state.theta[var56];
			if(!state.fixedFlag$sample58)
				DistributionSampling.sampleDirichlet(state.RNG$, state.alpha, state.noTopics, var57);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.z[((i$var71 - 0) / 1)][((j - 0) / 1)] = DistributionSampling.sampleCategorical(state.RNG$, state.theta[i$var71], state.noTopics);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
				if(!state.fixedFlag$sample42)
					inferSample42(var41);
			}
			for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
				if(!state.fixedFlag$sample58)
					inferSample58(var56);
			}
			for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
				for(int j = 0; j < state.length$documents[i$var71]; j += 1)
					inferSample90(i$var71, j);
			}
		} else {
			for(int i$var71 = (state.length$documents.length - ((((state.length$documents.length - 1) - 0) % 1) + 1)); i$var71 >= ((0 - 1) + 1); i$var71 -= 1) {
				for(int j = (state.length$documents[i$var71] - ((((state.length$documents[i$var71] - 1) - 0) % 1) + 1)); j >= ((0 - 1) + 1); j -= 1)
					inferSample90(i$var71, j);
			}
			for(int var56 = (state.length$documents.length - ((((state.length$documents.length - 1) - 0) % 1) + 1)); var56 >= ((0 - 1) + 1); var56 -= 1) {
				if(!state.fixedFlag$sample58)
					inferSample58(var56);
			}
			for(int var41 = (state.noTopics - ((((state.noTopics - 1) - 0) % 1) + 1)); var41 >= ((0 - 1) + 1); var41 -= 1) {
				if(!state.fixedFlag$sample42)
					inferSample42(var41);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var41 = 0; var41 < state.noTopics; var41 += 1) {
			if(!state.constrainedFlag$sample42[((var41 - 0) / 1)])
				drawValueSample42(var41);
		}
		for(int var56 = 0; var56 < state.length$documents.length; var56 += 1) {
			if(!state.constrainedFlag$sample58[((var56 - 0) / 1)])
				drawValueSample58(var56);
		}
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1) {
				if(!state.constrainedFlag$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)])
					drawValueSample90(i$var71, j);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$phi = 0.0;
		if(!state.fixedProbFlag$sample42)
			state.logProbability$var42 = Double.NaN;
		state.logProbability$theta = 0.0;
		if(!state.fixedProbFlag$sample58)
			state.logProbability$var57 = Double.NaN;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.logProbability$sample90[((i$var71 - 0) / 1)][((j - 0) / 1)] = Double.NaN;
		}
		state.logProbability$w = 0.0;
		for(int i$var71 = 0; i$var71 < state.length$documents.length; i$var71 += 1) {
			for(int j = 0; j < state.length$documents[i$var71]; j += 1)
				state.logProbability$sample93[((i$var71 - 0) / 1)][((j - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int i$var14 = 0; i$var14 < state.noTopics; i$var14 += 1)
			state.alpha[i$var14] = 0.1;
		for(int i$var27 = 0; i$var27 < state.vocabSize; i$var27 += 1)
			state.beta[i$var27] = 0.1;
		for(int index$constrainedFlag$sample90$1 = 0; index$constrainedFlag$sample90$1 < state.constrainedFlag$sample90.length; index$constrainedFlag$sample90$1 += 1) {
			boolean[] cv$constrainedFlag$sample90$1 = state.constrainedFlag$sample90[index$constrainedFlag$sample90$1];
			for(int index$constrainedFlag$sample90$2 = 0; index$constrainedFlag$sample90$2 < cv$constrainedFlag$sample90$1.length; index$constrainedFlag$sample90$2 += 1)
				cv$constrainedFlag$sample90$1[index$constrainedFlag$sample90$2] = true;
		}
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < state.constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			state.constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		for(int index$constrainedFlag$sample58$1 = 0; index$constrainedFlag$sample58$1 < state.constrainedFlag$sample58.length; index$constrainedFlag$sample58$1 += 1)
			state.constrainedFlag$sample58[index$constrainedFlag$sample58$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(state.fixedFlag$sample58)
			logProbabilityValue$sample58();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample58();
		logProbabilityValue$sample90();
		logProbabilityValue$sample93();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample58();
		logProbabilityValue$sample90();
		logProbabilityValue$sample93();
	}

	@Override
	public final void propagateObservedValues() {
		int[][] cv$source1 = state.documents;
		int[][] cv$target1 = state.w;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = cv$source1[cv$index1];
			int[] cv$target2 = cv$target1[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
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
		     + "model LDATest(int noTopics, int vocabSize, int[][] documents) {\n"
		     + "\n"
		     + "    double[] alpha = new double[noTopics];\n"
		     + "    for(int i:[0..noTopics))\n"
		     + "        alpha[i] = 0.1;\n"
		     + "\n"
		     + "    double[] beta = new double[vocabSize];\n"
		     + "    for(int i:[0..vocabSize))\n"
		     + "        beta[i] = 0.1;\n"
		     + "\n"
		     + "    double[][] phi = dirichlet(beta).sample(noTopics);\n"
		     + "    double[][] theta = dirichlet(alpha).sample(documents.length);\n"
		     + "    int[][] w = new int[documents.length][];\n"
		     + "\n"
		     + "    for(int i:[0..documents.length)) {\n"
		     + "        int[] t = new int[documents[i].length];\n"
		     + "        for(int j:[0..documents[i].length)) {\n"
		     + "            int z = categorical(theta[i]).sample();\n"
		     + "            t[j] = categorical(phi[z]).sample();\n"
		     + "        }\n"
		     + "        w[i] = t;\n"
		     + "    }\n"
		     + "\n"
		     + "    w.observe(documents);\n"
		     + "}\n"
		     + "";
	}
}