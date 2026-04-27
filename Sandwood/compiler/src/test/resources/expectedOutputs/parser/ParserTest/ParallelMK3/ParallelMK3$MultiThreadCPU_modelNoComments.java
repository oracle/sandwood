package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.ParallelMK3$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.ParallelMK3.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class ParallelMK3$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[] guard$sample21gaussian37$global;

		@Override
		public final void allocateScratch() {
			int cv$max_i = 0;
			cv$max_i = Math.max(cv$max_i, ((state.length$observed - 0) / 1));
			guard$sample21gaussian37$global = new boolean[cv$max_i];
		}
	}


	public ParallelMK3$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample21() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		{
			{
				for(int i = 0; i < state.length$observed; i += 1)
					state.indirection[i] = state.sample[i];
			}
		}
	}

	private final void inferSample21() {
		if(true) {
			state.constrainedFlag$sample21 = false;
			double[] cv$targetLocal = state.sample;
			double cv$originalProbability = 0.0;
			double cv$proposedProbability = 0.0;
			int cv$arrayLength = 10;
			int cv$indexToChange = (int)(0.0 + ((cv$arrayLength - 0.0) * DistributionSampling.sampleUniform(state.RNG$)));
			double cv$movementRatio = ((DistributionSampling.sampleBeta(state.RNG$, 5, 5) * 1.9999) - 1);
			double cv$proposedDifference;
			if((cv$movementRatio < 0))
				cv$proposedDifference = cv$targetLocal[cv$indexToChange];
			else {
				cv$proposedDifference = (1.0 - cv$targetLocal[cv$indexToChange]);
				for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1) {
					double cv$temp = (cv$targetLocal[cv$loopIndex] * (cv$arrayLength - 1));
					if((cv$temp < cv$proposedDifference))
						cv$proposedDifference = cv$temp;
				}
				for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1) {
					double cv$temp = (cv$targetLocal[cv$loopIndex] * (cv$arrayLength - 1));
					if((cv$temp < cv$proposedDifference))
						cv$proposedDifference = cv$temp;
				}
			}
			cv$proposedDifference = (cv$movementRatio * cv$proposedDifference);
			double cv$rebalanceValue = (cv$proposedDifference / (cv$arrayLength - 1));
			for(int cv$valuePos = 0; cv$valuePos < 2; cv$valuePos += 1) {
				if((state.constrainedFlag$sample21 || (cv$valuePos == 0))) {
					double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
					double cv$reachedDistributionSourceRV = 0.0;
					double cv$accumulatedDistributionProbabilities = 0.0;
					if((cv$valuePos == 1)) {
						{
							for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
								cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] - cv$rebalanceValue);
							cv$targetLocal[cv$indexToChange] = (cv$targetLocal[cv$indexToChange] + cv$proposedDifference);
							for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
								cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] - cv$rebalanceValue);
							{
								{
									for(int i = 0; i < state.length$observed; i += 1)
										state.indirection[i] = state.sample[i];
								}
							}
						}
					}
					{
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + 1.0);
						double cv$accumulatedProbabilities = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$targetLocal, state.v, 10));
						{
							{
								boolean[] guard$sample21gaussian37 = scratch.guard$sample21gaussian37$global;
								{
									for(int i = 0; i < state.length$observed; i += 1)
										guard$sample21gaussian37[((i - 0) / 1)] = false;
								}
								{
									for(int i = 0; i < state.length$observed; i += 1) {
										for(int index$i$3_2 = 0; index$i$3_2 < state.length$observed; index$i$3_2 += 1) {
											if((i == index$i$3_2))
												guard$sample21gaussian37[((i - 0) / 1)] = false;
										}
									}
								}
								{
									for(int i = 0; i < state.length$observed; i += 1) {
										if(!guard$sample21gaussian37[((i - 0) / 1)]) {
											guard$sample21gaussian37[((i - 0) / 1)] = true;
											{
												{
													boolean cv$sampleConstrained = true;
													if(cv$sampleConstrained) {
														state.constrainedFlag$sample21 = true;
														double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
														double cv$consumerDistributionProbabilityAccumulator = 1.0;
														{
															{
																{
																	{
																		{
																			double var35 = state.sample[i];
																			double var36 = state.indirection[i];
																			if(((Math.log(1.0) + ((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - var35) / Math.sqrt(var36))) - (0.5 * Math.log(var36))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																				cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - var35) / Math.sqrt(var36))) - (0.5 * Math.log(var36))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																			else {
																				if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																					cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - var35) / Math.sqrt(var36))) - (0.5 * Math.log(var36))):Double.NEGATIVE_INFINITY));
																				else
																					cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - var35) / Math.sqrt(var36))) - (0.5 * Math.log(var36))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((state.generated[i] - var35) / Math.sqrt(var36))) - (0.5 * Math.log(var36))):Double.NEGATIVE_INFINITY)));
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
								{
									for(int i = 0; i < state.length$observed; i += 1) {
										double traceTempVariable$var36$5_2 = state.sample[i];
										for(int index$i$5_3 = 0; index$i$5_3 < state.length$observed; index$i$5_3 += 1) {
											if((i == index$i$5_3)) {
												if(!guard$sample21gaussian37[((i - 0) / 1)]) {
													guard$sample21gaussian37[((i - 0) / 1)] = true;
													{
														{
															boolean cv$sampleConstrained = true;
															if(cv$sampleConstrained) {
																state.constrainedFlag$sample21 = true;
																double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
																double cv$consumerDistributionProbabilityAccumulator = 1.0;
																{
																	{
																		{
																			{
																				{
																					double var35 = state.sample[index$i$5_3];
																					if(((Math.log(1.0) + ((0.0 < traceTempVariable$var36$5_2)?(DistributionSampling.logProbabilityGaussian(((state.generated[index$i$5_3] - var35) / Math.sqrt(traceTempVariable$var36$5_2))) - (0.5 * Math.log(traceTempVariable$var36$5_2))):Double.NEGATIVE_INFINITY)) < cv$accumulatedConsumerProbabilities))
																						cv$accumulatedConsumerProbabilities = (Math.log((Math.exp(((Math.log(1.0) + ((0.0 < traceTempVariable$var36$5_2)?(DistributionSampling.logProbabilityGaussian(((state.generated[index$i$5_3] - var35) / Math.sqrt(traceTempVariable$var36$5_2))) - (0.5 * Math.log(traceTempVariable$var36$5_2))):Double.NEGATIVE_INFINITY)) - cv$accumulatedConsumerProbabilities)) + 1)) + cv$accumulatedConsumerProbabilities);
																					else {
																						if((cv$accumulatedConsumerProbabilities == Double.NEGATIVE_INFINITY))
																							cv$accumulatedConsumerProbabilities = (Math.log(1.0) + ((0.0 < traceTempVariable$var36$5_2)?(DistributionSampling.logProbabilityGaussian(((state.generated[index$i$5_3] - var35) / Math.sqrt(traceTempVariable$var36$5_2))) - (0.5 * Math.log(traceTempVariable$var36$5_2))):Double.NEGATIVE_INFINITY));
																						else
																							cv$accumulatedConsumerProbabilities = (Math.log((Math.exp((cv$accumulatedConsumerProbabilities - (Math.log(1.0) + ((0.0 < traceTempVariable$var36$5_2)?(DistributionSampling.logProbabilityGaussian(((state.generated[index$i$5_3] - var35) / Math.sqrt(traceTempVariable$var36$5_2))) - (0.5 * Math.log(traceTempVariable$var36$5_2))):Double.NEGATIVE_INFINITY)))) + 1)) + (Math.log(1.0) + ((0.0 < traceTempVariable$var36$5_2)?(DistributionSampling.logProbabilityGaussian(((state.generated[index$i$5_3] - var35) / Math.sqrt(traceTempVariable$var36$5_2))) - (0.5 * Math.log(traceTempVariable$var36$5_2))):Double.NEGATIVE_INFINITY)));
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
					double cv$ratio = (cv$proposedProbability - cv$originalProbability);
					if((cv$valuePos == 1)) {
						if(((cv$ratio <= Math.log((0.0 + ((1.0 - 0.0) * DistributionSampling.sampleUniform(state.RNG$))))) || Double.isNaN(cv$ratio))) {
							for(int cv$loopIndex = 0; cv$loopIndex < cv$indexToChange; cv$loopIndex += 1)
								cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] + cv$rebalanceValue);
							cv$targetLocal[cv$indexToChange] = (cv$targetLocal[cv$indexToChange] - cv$proposedDifference);
							for(int cv$loopIndex = (cv$indexToChange + 1); cv$loopIndex < cv$arrayLength; cv$loopIndex += 1)
								cv$targetLocal[cv$loopIndex] = (cv$targetLocal[cv$loopIndex] + cv$rebalanceValue);
							{
								{
									for(int i = 0; i < state.length$observed; i += 1)
										state.indirection[i] = state.sample[i];
								}
							}
						}
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!state.fixedProbFlag$sample21) {
			double cv$accumulator = 0.0;
			double cv$sampleAccumulator = 0.0;
			double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
			double cv$probabilityReached = 0.0;
			{
				{
					double[] cv$sampleValue = state.sample;
					{
						{
							double cv$weightedProbability = (Math.log(1.0) + DistributionSampling.logProbabilityDirichlet(cv$sampleValue, state.v, 10));
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
			state.logProbability$sample = cv$sampleProbability;
			boolean cv$guard$indirection = false;
			{
				{
					for(int i = 0; i < state.length$observed; i += 1) {
						if(!cv$guard$indirection) {
							cv$guard$indirection = true;
							state.logProbability$indirection = (state.logProbability$indirection + cv$accumulator);
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample21 = state.fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			double cv$rvAccumulator = 0.0;
			double cv$sampleValue = state.logProbability$sample;
			cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
			cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			boolean cv$guard$indirection = false;
			{
				{
					for(int i = 0; i < state.length$observed; i += 1) {
						if(!cv$guard$indirection) {
							cv$guard$indirection = true;
							state.logProbability$indirection = (state.logProbability$indirection + cv$accumulator);
						}
					}
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample38() {
		if(!state.fixedProbFlag$sample38) {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.length$observed; i += 1) {
				double cv$sampleAccumulator = 0.0;
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				{
					{
						double cv$sampleValue = state.generated[i];
						{
							{
								double var35 = state.sample[i];
								double var36 = state.indirection[i];
								double cv$weightedProbability = (Math.log(1.0) + ((0.0 < var36)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - var35) / Math.sqrt(var36))) - (0.5 * Math.log(var36))):Double.NEGATIVE_INFINITY));
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
				state.logProbability$sample38[((i - 0) / 1)] = cv$sampleProbability;
			}
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample38 = state.fixedFlag$sample21;
		} else {
			double cv$accumulator = 0.0;
			boolean cv$sampleReached = false;
			for(int i = 0; i < state.length$observed; i += 1) {
				double cv$rvAccumulator = 0.0;
				double cv$sampleValue = state.logProbability$sample38[((i - 0) / 1)];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				cv$sampleReached = true;
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
			}
			state.logProbability$generated = (state.logProbability$generated + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample21)
							state.indirection[i] = state.sample[i];
						state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + state.sample[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.indirection[i] = state.sample[i];
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						state.indirection[i] = state.sample[i];
						state.generated[i] = ((Math.sqrt(state.indirection[i]) * DistributionSampling.sampleGaussian(RNG$1)) + state.sample[i]);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1) {
						if(!state.fixedFlag$sample21)
							state.indirection[i] = state.sample[i];
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample21)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 10, state.sample);
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.indirection[i] = state.sample[i];
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample21)
				inferSample21();
		} else {
			if(!state.fixedFlag$sample21)
				inferSample21();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample21)
			drawValueSample21();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$indirection = 0.0;
		if(!state.fixedProbFlag$sample21)
			state.logProbability$sample = Double.NaN;
		state.logProbability$generated = 0.0;
		if(!state.fixedProbFlag$sample38) {
			for(int i = 0; i < state.length$observed; i += 1)
				state.logProbability$sample38[((i - 0) / 1)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int var17 = 0; var17 < 10; var17 += 1)
			state.v[var17] = 0.1;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		logProbabilityValue$sample38();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample38();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample38();
	}

	@Override
	public final void propagateObservedValues() {
		double[] cv$source1 = state.observed;
		double[] cv$target1 = state.generated;
		int cv$length1 = cv$target1.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			cv$target1[cv$index1] = cv$source1[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 0, state.length$observed, 1,
			(int forStart$i, int forEnd$i, int threadID$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i = forStart$i; i < forEnd$i; i += 1)
						state.indirection[i] = state.sample[i];
			}
		);
	}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model ParallelMK3(double[] observed) {\n"
		     + "    double[] generated = new double[observed.length];\n"
		     + "    double[] indirection = new double[observed.length];\n"
		     + "    double[] v = new double[10] <~ 0.1;\n"
		     + "\n"
		     + "\n"
		     + "    double[] sample = dirichlet(v).sample();\n"
		     + "    for(int i=0; i<observed.length; i++) {\n"
		     + "        indirection[i] = sample[i];\n"
		     + "        generated[i] = gaussian(sample[i], indirection[i]).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    generated.observe(observed);\n"
		     + "}";
	}
}