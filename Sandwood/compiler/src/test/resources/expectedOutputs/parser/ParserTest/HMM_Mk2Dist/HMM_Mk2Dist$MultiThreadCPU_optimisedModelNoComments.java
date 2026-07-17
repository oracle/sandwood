package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMM_Mk2Dist$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMM_Mk2Dist.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Mk2Dist$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[][] cv$distributionAccumulator$var122;
		double[] cv$distributionAccumulator$var91;
		double[][] cv$var123$stateProbabilityGlobal;
		double[][] cv$var42$countGlobal;
		double[][] cv$var56$countGlobal;
		double[] cv$var75$countGlobal;
		double[] cv$var77$stateProbabilityGlobal;
		double[][] cv$var92$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			{
				int cv$threadCount = threadCount();
				cv$var42$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var42$countGlobal[cv$index] = new double[state.noStates];
			}
			{
				int cv$threadCount = threadCount();
				cv$var56$countGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var56$countGlobal[cv$index] = new double[state.noEvents];
			}
			cv$var75$countGlobal = new double[state.noStates];
			cv$distributionAccumulator$var91 = new double[state.noStates];
			cv$var77$stateProbabilityGlobal = new double[state.noStates];
			{
				int cv$threadCount = threadCount();
				cv$distributionAccumulator$var122 = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$distributionAccumulator$var122[cv$index] = new double[state.noStates];
			}
			{
				int cv$threadCount = threadCount();
				cv$var92$stateProbabilityGlobal = new double[cv$threadCount][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					cv$var92$stateProbabilityGlobal[cv$index] = new double[state.noStates];
			}
			int cv$threadCount = threadCount();
			cv$var123$stateProbabilityGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var123$stateProbabilityGlobal[cv$index] = new double[state.noStates];
		}
	}


	public HMM_Mk2Dist$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		state.st[i$var104][j$var115] = DistributionSampling.sampleCategorical(RNG$, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
	}

	private final void drawValueSample42(int var41, int threadID$cv$var41, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, state.noStates, state.m[var41]);
	}

	private final void drawValueSample57(int var55, int threadID$cv$var55, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v2, state.noEvents, state.bias[var55]);
	}

	private final void drawValueSample78() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
	}

	private final void drawValueSample80() {
		state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
	}

	private final void drawValueSample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		state.st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$, state.m[state.initialState], state.noStates);
	}

	private final void inferSample126(int i$var104, int j$var115, int threadID$cv$i$var104, Rng RNG$) {
		state.constrainedFlag$sample126[i$var104][(j$var115 - 1)] = false;
		int cv$numStates = 0;
		if((1 == j$var115)) {
			if(state.fixedFlag$sample95) {
				int var41 = state.st[i$var104][0];
				if(((0 <= var41) && (var41 < state.noStates)))
					cv$numStates = Math.max(0, state.noStates);
			} else {
				if((0 < state.noStates))
					cv$numStates = state.noStates;
			}
		}
		if((0 < state.noStates)) {
			int index$j$13 = (j$var115 - 1);
			if(((1 <= index$j$13) && !(index$j$13 == j$var115)))
				cv$numStates = state.noStates;
		}
		double[] cv$stateProbabilityLocal = scratch.cv$var123$stateProbabilityGlobal[threadID$cv$i$var104];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == j$var115)) {
				if(state.fixedFlag$sample95) {
					int var41 = state.st[i$var104][0];
					if(((0 <= var41) && (var41 < state.noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var121 = state.m[state.st[i$var104][0]];
						double cv$accumulatedProbabilities = ((((cv$valuePos < state.noStates) && (0.0 <= var121[cv$valuePos])) && (var121[cv$valuePos] <= 1.0))?Math.log(var121[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < state.length$eventsMeasured[i$var104])) {
							state.constrainedFlag$sample126[i$var104][0] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							if((cv$valuePos < state.noStates)) {
								double[] var153 = state.bias[cv$valuePos];
								cv$accumulatedConsumerProbabilities = ((((((1.0 <= state.events[i$var104][1]) && (state.events[i$var104][1] < (state.noEvents + 1))) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var104][1] - 1)])) && (var153[(state.events[i$var104][1] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var104][1] - 1)]):Double.NEGATIVE_INFINITY);
								cv$consumerDistributionProbabilityAccumulator = 0.0;
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
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample95$22 = 0; index$sample95$22 < state.noStates; index$sample95$22 += 1) {
						double cv$probabilitySample95Value23 = state.distribution$sample95[i$var104][index$sample95$22];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample95Value23);
						double[] var121 = state.m[index$sample95$22];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample95Value23) + (((0.0 <= var121[cv$valuePos]) && (var121[cv$valuePos] <= 1.0))?Math.log(var121[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < state.length$eventsMeasured[i$var104])) {
							state.constrainedFlag$sample126[i$var104][0] = true;
							double[] var153 = state.bias[cv$valuePos];
							cv$accumulatedProbabilities = (((((((1.0 <= state.events[i$var104][1]) && (state.events[i$var104][1] < (state.noEvents + 1))) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var104][1] - 1)])) && (var153[(state.events[i$var104][1] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var104][1] - 1)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				}
			}
			int index$j$30 = (j$var115 - 1);
			if(((1 <= index$j$30) && !(index$j$30 == j$var115))) {
				for(int index$sample126$31 = 0; index$sample126$31 < state.noStates; index$sample126$31 += 1) {
					double cv$probabilitySample126Value32 = state.distribution$sample126[i$var104][(index$j$30 - 1)][index$sample126$31];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample126Value32);
					double[] var121 = state.m[index$sample126$31];
					state.constrainedFlag$sample126[i$var104][(j$var115 - 1)] = true;
					double[] var153 = state.bias[index$sample126$31];
					double cv$accumulatedProbabilities = ((((((((1.0 <= state.events[i$var104][j$var115]) && (state.events[i$var104][j$var115] < (state.noEvents + 1))) && (0 < state.noEvents)) && (0.0 <= var153[(state.events[i$var104][j$var115] - 1)])) && (var153[(state.events[i$var104][j$var115] - 1)] <= 1.0))?Math.log(var153[(state.events[i$var104][j$var115] - 1)]):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample126Value32)) + (((0.0 <= var121[cv$valuePos]) && (var121[cv$valuePos] <= 1.0))?Math.log(var121[cv$valuePos]):Double.NEGATIVE_INFINITY));
					if((cv$accumulatedProbabilities < cv$stateProbabilityValue))
						cv$stateProbabilityValue = (Math.log((Math.exp((cv$accumulatedProbabilities - cv$stateProbabilityValue)) + 1)) + cv$stateProbabilityValue);
					else {
						if((cv$stateProbabilityValue == Double.NEGATIVE_INFINITY))
							cv$stateProbabilityValue = cv$accumulatedProbabilities;
						else
							cv$stateProbabilityValue = (Math.log((Math.exp((cv$stateProbabilityValue - cv$accumulatedProbabilities)) + 1)) + cv$accumulatedProbabilities);
					}
				}
			}
			int index$j$57_3 = (j$var115 + 1);
			if((index$j$57_3 < state.length$eventsMeasured[i$var104])) {
				double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var122[threadID$cv$i$var104];
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					cv$accumulatedConsumerDistributions[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < state.noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == j$var115)) {
						if(state.fixedFlag$sample95) {
							int index$var41$68_1 = state.st[i$var104][0];
							if(((0 <= index$var41$68_1) && (index$var41$68_1 < state.noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample95$64 = 0; index$sample95$64 < state.noStates; index$sample95$64 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample95[i$var104][index$sample95$64]);
						}
					}
					int index$j$72 = (j$var115 - 1);
					if((((1 <= index$j$72) && !(index$j$72 == j$var115)) && !(index$j$72 == index$j$57_3))) {
						for(int index$sample126$73 = 0; index$sample126$73 < state.noStates; index$sample126$73 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample126[i$var104][(index$j$72 - 1)][index$sample126$73]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], state.noStates);
				}
				double[] cv$sampleDistribution = state.distribution$sample126[i$var104][(index$j$57_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$stateProbabilityLocal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample126[i$var104][(j$var115 - 1)]) {
			double[] cv$localProbability = state.distribution$sample126[i$var104][(j$var115 - 1)];
			double cv$logSum;
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
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
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

	private final void inferSample42(int var41, int threadID$cv$var41, Rng RNG$) {
		state.constrainedFlag$sample42[var41] = false;
		double[] cv$countLocal = scratch.cv$var42$countGlobal[threadID$cv$var41];
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(((var41 == state.initialState) && state.fixedFlag$sample95)) {
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
				state.constrainedFlag$sample42[var41] = true;
				cv$countLocal[state.st[i$var87][0]] = (cv$countLocal[state.st[i$var87][0]] + 1.0);
			}
		}
		if(state.fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				if((1 < state.length$eventsMeasured[i$var104])) {
					if(state.fixedFlag$sample95) {
						if((var41 == state.st[i$var104][0])) {
							state.constrainedFlag$sample42[var41] = true;
							cv$countLocal[state.st[i$var104][1]] = (cv$countLocal[state.st[i$var104][1]] + 1.0);
						}
					} else {
						state.constrainedFlag$sample42[var41] = true;
						cv$countLocal[state.st[i$var104][1]] = (cv$countLocal[state.st[i$var104][1]] + state.distribution$sample95[i$var104][var41]);
					}
				}
			}
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 2; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
					if((var41 == state.st[i$var104][(j$var115 - 1)])) {
						state.constrainedFlag$sample42[var41] = true;
						cv$countLocal[state.st[i$var104][j$var115]] = (cv$countLocal[state.st[i$var104][j$var115]] + 1.0);
					}
				}
			}
		}
		if(((var41 == state.initialState) && !state.fixedFlag$sample95)) {
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + state.distribution$sample95[i$var87][cv$loopIndex]);
			}
		}
		if(!state.fixedFlag$sample126) {
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				if((1 < state.length$eventsMeasured[i$var104])) {
					if(state.fixedFlag$sample95) {
						if((var41 == state.st[i$var104][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
								cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + state.distribution$sample126[i$var104][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = state.distribution$sample95[i$var104][var41];
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample126[i$var104][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
					int index$j$59 = (j$var115 - 1);
					if((1 <= index$j$59)) {
						double cv$distributionProbability = state.distribution$sample126[i$var104][(index$j$59 - 1)][var41];
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample126[i$var104][(j$var115 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(state.constrainedFlag$sample42[var41])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var41], state.noStates);
	}

	private final void inferSample57(int var55, int threadID$cv$var55, Rng RNG$) {
		state.constrainedFlag$sample57[var55] = false;
		double[] cv$countLocal = scratch.cv$var56$countGlobal[threadID$cv$var55];
		for(int cv$loopIndex = 0; cv$loopIndex < state.noEvents; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
			for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
				if(state.fixedFlag$sample126) {
					if((var55 == state.st[i$var136][j$var149])) {
						state.constrainedFlag$sample57[var55] = true;
						cv$countLocal[(state.events[i$var136][j$var149] - 1)] = (cv$countLocal[(state.events[i$var136][j$var149] - 1)] + 1.0);
					}
				} else {
					state.constrainedFlag$sample57[var55] = true;
					cv$countLocal[(state.events[i$var136][j$var149] - 1)] = (cv$countLocal[(state.events[i$var136][j$var149] - 1)] + state.distribution$sample126[i$var136][(j$var149 - 1)][var55]);
				}
			}
		}
		if(state.constrainedFlag$sample57[var55])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v2, cv$countLocal, state.bias[var55], state.noEvents);
	}

	private final void inferSample78() {
		state.constrainedFlag$sample78 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			scratch.cv$var75$countGlobal[cv$loopIndex] = 0.0;
		if((state.fixedFlag$sample80 || state.constrainedFlag$sample80)) {
			state.constrainedFlag$sample78 = true;
			scratch.cv$var75$countGlobal[state.initialState] = (scratch.cv$var75$countGlobal[state.initialState] + 1.0);
		}
		if(state.constrainedFlag$sample78)
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var75$countGlobal, state.weights, state.noStates);
	}

	private final void inferSample80() {
		state.constrainedFlag$sample80 = false;
		int cv$numStates = Math.max(0, state.noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			state.initialState = cv$valuePos;
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.noStates) && (0 < state.noStates)) && (0.0 <= state.weights[cv$valuePos])) && (state.weights[cv$valuePos] <= 1.0))?Math.log(state.weights[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if(state.fixedFlag$sample95) {
				for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
					state.constrainedFlag$sample80 = true;
					double[] var90 = state.m[cv$valuePos];
					cv$accumulatedProbabilities = (((((((0.0 <= state.st[i$var87][0]) && (state.st[i$var87][0] < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[state.st[i$var87][0]])) && (var90[state.st[i$var87][0]] <= 1.0))?Math.log(var90[state.st[i$var87][0]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			} else {
				for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
					for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
						scratch.cv$distributionAccumulator$var91[cv$i] = 0.0;
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var91, 1.0, state.m[cv$valuePos], state.noStates);
					double[] cv$sampleDistribution = state.distribution$sample95[i$var87];
					double cv$overlap = 0.0;
					for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
						double cv$normalisedDistValue = scratch.cv$distributionAccumulator$var91[cv$i];
						double cv$sampleDistValue = cv$sampleDistribution[cv$i];
						if((cv$sampleDistValue < cv$normalisedDistValue))
							cv$overlap = (cv$overlap + cv$sampleDistValue);
						else
							cv$overlap = (cv$overlap + cv$normalisedDistValue);
					}
					cv$accumulatedDistributionProbabilities = (cv$accumulatedDistributionProbabilities + Math.log(cv$overlap));
				}
			}
			scratch.cv$var77$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample80) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var77$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var77$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var77$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var77$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var77$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var77$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var77$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var77$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var77$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void inferSample95(int i$var87, int threadID$cv$i$var87, Rng RNG$) {
		state.constrainedFlag$sample95[i$var87] = false;
		int cv$numStates = Math.max(0, state.noStates);
		double[] cv$stateProbabilityLocal = scratch.cv$var92$stateProbabilityGlobal[threadID$cv$i$var87];
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double[] var90 = state.m[state.initialState];
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.noStates) && (0 < state.noStates)) && (0.0 <= var90[cv$valuePos])) && (var90[cv$valuePos] <= 1.0))?Math.log(var90[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((1 < state.length$eventsMeasured[i$var87])) {
				if(state.fixedFlag$sample126) {
					state.constrainedFlag$sample95[i$var87] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < state.noStates)) {
						double[] var121 = state.m[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (((((0.0 <= state.st[i$var87][1]) && (state.st[i$var87][1] < state.noStates)) && (0.0 <= var121[state.st[i$var87][1]])) && (var121[state.st[i$var87][1]] <= 1.0))?Math.log(var121[state.st[i$var87][1]]):Double.NEGATIVE_INFINITY);
						cv$consumerDistributionProbabilityAccumulator = 0.0;
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
				} else {
					double[] cv$accumulatedConsumerDistributions = scratch.cv$distributionAccumulator$var122[threadID$cv$i$var87];
					for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
						cv$accumulatedConsumerDistributions[cv$i] = 0.0;
					double cv$reachedDistributionProbability = 0.0;
					if((cv$valuePos < state.noStates)) {
						cv$reachedDistributionProbability = 1.0;
						DistributionSampling.addProbabilityDistributionCategorical(cv$accumulatedConsumerDistributions, 1.0, state.m[cv$valuePos], state.noStates);
					}
					double[] cv$sampleDistribution = state.distribution$sample126[i$var87][0];
					double cv$overlap = 0.0;
					for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
						double cv$normalisedDistValue = (cv$accumulatedConsumerDistributions[cv$i] / cv$reachedDistributionProbability);
						double cv$sampleDistValue = cv$sampleDistribution[cv$i];
						if((cv$sampleDistValue < cv$normalisedDistValue))
							cv$overlap = (cv$overlap + cv$sampleDistValue);
						else
							cv$overlap = (cv$overlap + cv$normalisedDistValue);
					}
					cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
				}
			}
			cv$stateProbabilityLocal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample95[i$var87]) {
			double[] cv$localProbability = state.distribution$sample95[i$var87];
			double cv$logSum;
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
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
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

	private final void logProbabilityDistribution$sample126() {
		if(!state.fixedProbFlag$sample126) {
			if(state.fixedFlag$sample126) {
				double cv$accumulator = 0.0;
				for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
					for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = state.st[i$var104][j$var115];
						if((1 == j$var115)) {
							if(state.fixedFlag$sample95) {
								int var41 = state.st[i$var104][0];
								if(((0 <= var41) && (var41 < state.noStates))) {
									double[] var121 = state.m[state.st[i$var104][0]];
									cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample95$6 = 0; index$sample95$6 < state.noStates; index$sample95$6 += 1) {
									double cv$probabilitySample95Value7 = state.distribution$sample95[i$var104][index$sample95$6];
									double[] var121 = state.m[index$sample95$6];
									double cv$weightedProbability = (Math.log(cv$probabilitySample95Value7) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample95Value7);
								}
							}
						}
						if((2 <= j$var115)) {
							int var41 = state.st[i$var104][(j$var115 - 1)];
							if(((0 <= var41) && (var41 < state.noStates))) {
								double[] var121 = state.m[state.st[i$var104][(j$var115 - 1)]];
								double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						state.logProbability$sample126[i$var104][(j$var115 - 1)] = cv$distributionAccumulator;
					}
				}
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample126 = (state.fixedFlag$sample42 && state.fixedFlag$sample95);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample126[i$var104][(j$var115 - 1)]);
			}
			if(state.fixedFlag$sample126)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample126)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample159() {
		if(!state.fixedProbFlag$sample159) {
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = (state.events[i$var136][j$var149] - 1);
					if(state.fixedFlag$sample126) {
						int var55 = state.st[i$var136][j$var149];
						if(((0 <= var55) && (var55 < state.noStates))) {
							double[] var153 = state.bias[state.st[i$var136][j$var149]];
							cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							cv$probabilityReached = 1.0;
						}
					} else {
						for(int index$sample126$13 = 0; index$sample126$13 < state.noStates; index$sample126$13 += 1) {
							double cv$probabilitySample126Value14 = state.distribution$sample126[i$var136][(j$var149 - 1)][index$sample126$13];
							double[] var153 = state.bias[index$sample126$13];
							double cv$weightedProbability = (Math.log(cv$probabilitySample126Value14) + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample126Value14);
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample159[i$var136][(j$var149 - 1)] = cv$distributionAccumulator;
				}
			}
			state.logProbability$events = (state.logProbability$events + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample159 = ((state.fixedFlag$sample57 && state.fixedFlag$sample95) && state.fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample159[i$var136][(j$var149 - 1)]);
			}
			state.logProbability$events = (state.logProbability$events + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample95() {
		if(!state.fixedProbFlag$sample95) {
			if(state.fixedFlag$sample95) {
				double cv$accumulator = 0.0;
				for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
					int cv$sampleValue = state.st[i$var87][0];
					double[] var90 = state.m[state.initialState];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[cv$sampleValue])) && (var90[cv$sampleValue] <= 1.0))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample95[i$var87] = cv$distributionAccumulator;
				}
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample95 = (state.fixedFlag$sample42 && state.fixedFlag$sample80);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample95[i$var87]);
			if(state.fixedFlag$sample95)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample95)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample126() {
		if(!state.fixedProbFlag$sample126) {
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
					int cv$sampleValue = state.st[i$var104][j$var115];
					double[] var121 = state.m[state.st[i$var104][(j$var115 - 1)]];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var121[cv$sampleValue])) && (var121[cv$sampleValue] <= 1.0))?Math.log(var121[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample126[i$var104][(j$var115 - 1)] = cv$distributionAccumulator;
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample126)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample126 = ((state.fixedFlag$sample126 && state.fixedFlag$sample42) && state.fixedFlag$sample95);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample126[i$var104][(j$var115 - 1)]);
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample126)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample159() {
		if(!state.fixedProbFlag$sample159) {
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1) {
					int cv$sampleValue = (state.events[i$var136][j$var149] - 1);
					double[] var153 = state.bias[state.st[i$var136][j$var149]];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noEvents)) && (0 < state.noEvents)) && (0.0 <= var153[cv$sampleValue])) && (var153[cv$sampleValue] <= 1.0))?Math.log(var153[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample159[i$var136][(j$var149 - 1)] = cv$distributionAccumulator;
				}
			}
			state.logProbability$events = (state.logProbability$events + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample159 = ((state.fixedFlag$sample57 && state.fixedFlag$sample95) && state.fixedFlag$sample126);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample159[i$var136][(j$var149 - 1)]);
			}
			state.logProbability$events = (state.logProbability$events + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample42() {
		if(!state.fixedProbFlag$sample42) {
			double cv$sampleAccumulator = 0.0;
			for(int var41 = 0; var41 < state.noStates; var41 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var41], state.v, state.noStates));
			state.logProbability$var42 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample42 = state.fixedFlag$sample42;
		} else {
			state.logProbability$m = (state.logProbability$m + state.logProbability$var42);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var42);
			if(state.fixedFlag$sample42)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var42);
		}
	}

	private final void logProbabilityValue$sample57() {
		if(!state.fixedProbFlag$sample57) {
			double cv$sampleAccumulator = 0.0;
			for(int var55 = 0; var55 < state.noStates; var55 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.bias[var55], state.v2, state.noEvents));
			state.logProbability$var56 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample57 = state.fixedFlag$sample57;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var56);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var56);
			if(state.fixedFlag$sample57)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var56);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!state.fixedProbFlag$sample78) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.weights, state.v, state.noStates);
			state.logProbability$weights = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample78)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample78 = state.fixedFlag$sample78;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$weights);
			if(state.fixedFlag$sample78)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$weights);
		}
	}

	private final void logProbabilityValue$sample80() {
		if(!state.fixedProbFlag$sample80) {
			double cv$distributionAccumulator = ((((((0.0 <= state.initialState) && (state.initialState < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.weights[state.initialState])) && (state.weights[state.initialState] <= 1.0))?Math.log(state.weights[state.initialState]):Double.NEGATIVE_INFINITY);
			state.logProbability$initialState = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample80)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample80 = (state.fixedFlag$sample80 && state.fixedFlag$sample78);
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$initialState);
			if(state.fixedFlag$sample80)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$initialState);
		}
	}

	private final void logProbabilityValue$sample95() {
		if(!state.fixedProbFlag$sample95) {
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1) {
				int cv$sampleValue = state.st[i$var87][0];
				double[] var90 = state.m[state.initialState];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var90[cv$sampleValue])) && (var90[cv$sampleValue] <= 1.0))?Math.log(var90[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample95[i$var87] = cv$distributionAccumulator;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample95)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample95 = ((state.fixedFlag$sample95 && state.fixedFlag$sample42) && state.fixedFlag$sample80);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample95[i$var87]);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample95)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample42)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var41]);
				}
			);

		if(!state.fixedFlag$sample57)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, state.bias[var55]);
				}
			);

		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							state.st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.initialState], state.noStates);
				}
			);

		if(!state.fixedFlag$sample126)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = state.st[i$var104];
							for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
						}
				}
			);

		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
						int i$var136 = index$i$var136;
						int threadID$i$var136 = threadID$index$i$var136;
						int[] var150 = state.events[i$var136];
						parallelFor(RNG$1, 1, state.length$eventsMeasured[i$var136], 1,
							(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1)
										var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, state.bias[state.st[i$var136][j$var149]], state.noEvents) + 1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample42)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var41]);
				}
			);

		if(!state.fixedFlag$sample57)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, state.bias[var55]);
				}
			);

		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
							double[] cv$distribution$sample95 = state.distribution$sample95[i$var87];
							double[] var90 = state.m[state.initialState];
							for(int index$var91 = 0; index$var91 < state.noStates; index$var91 += 1)
								cv$distribution$sample95[index$var91] = (((0.0 <= var90[index$var91]) && (var90[index$var91] <= 1.0))?var90[index$var91]:0.0);
						}
				}
			);

		if(!state.fixedFlag$sample126)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
								double[] cv$distribution$sample126 = state.distribution$sample126[i$var104][(j$var115 - 1)];
								for(int index$var122 = 0; index$var122 < state.noStates; index$var122 += 1)
									cv$distribution$sample126[index$var122] = 0.0;
								if((1 == j$var115)) {
									if(state.fixedFlag$sample95) {
										int var41 = state.st[i$var104][0];
										if(((0 <= var41) && (var41 < state.noStates))) {
											double[] var121 = state.m[state.st[i$var104][0]];
											for(int index$var122 = 0; index$var122 < state.noStates; index$var122 += 1)
												cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (((0.0 <= var121[index$var122]) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0));
										}
									} else {
										for(int index$sample95$3 = 0; index$sample95$3 < state.noStates; index$sample95$3 += 1) {
											double cv$probabilitySample95Value4 = state.distribution$sample95[i$var104][index$sample95$3];
											double[] var121 = state.m[index$sample95$3];
											for(int index$var122 = 0; index$var122 < state.noStates; index$var122 += 1)
												cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample95Value4 * (((0.0 <= var121[index$var122]) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0)));
										}
									}
								}
								int index$j$11 = (j$var115 - 1);
								if((1 <= index$j$11)) {
									for(int index$sample126$12 = 0; index$sample126$12 < state.noStates; index$sample126$12 += 1) {
										double cv$probabilitySample126Value13 = state.distribution$sample126[i$var104][(index$j$11 - 1)][index$sample126$12];
										double[] var121 = state.m[index$sample126$12];
										for(int index$var122 = 0; index$var122 < state.noStates; index$var122 += 1)
											cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] + (cv$probabilitySample126Value13 * (((0.0 <= var121[index$var122]) && (var121[index$var122] <= 1.0))?var121[index$var122]:0.0)));
									}
								}
								double cv$var122$sum = 0.0;
								for(int index$var122 = 0; index$var122 < state.noStates; index$var122 += 1)
									cv$var122$sum = (cv$var122$sum + cv$distribution$sample126[index$var122]);
								for(int index$var122 = 0; index$var122 < state.noStates; index$var122 += 1)
									cv$distribution$sample126[index$var122] = (cv$distribution$sample126[index$var122] / cv$var122$sum);
							}
						}
				}
			);

	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample42)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var41]);
				}
			);

		if(!state.fixedFlag$sample57)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, state.bias[var55]);
				}
			);

		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							state.st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.initialState], state.noStates);
				}
			);

		if(!state.fixedFlag$sample126)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = state.st[i$var104];
							for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
						}
				}
			);

		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$index$i$var136, int forEnd$index$i$var136, int threadID$index$i$var136, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i$var136 = forStart$index$i$var136; index$i$var136 < forEnd$index$i$var136; index$i$var136 += 1) {
						int i$var136 = index$i$var136;
						int threadID$i$var136 = threadID$index$i$var136;
						int[] var150 = state.events[i$var136];
						parallelFor(RNG$1, 1, state.length$eventsMeasured[i$var136], 1,
							(int forStart$j$var149, int forEnd$j$var149, int threadID$j$var149, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var149 = forStart$j$var149; j$var149 < forEnd$j$var149; j$var149 += 1)
										var150[j$var149] = (DistributionSampling.sampleCategorical(RNG$2, state.bias[state.st[i$var136][j$var149]], state.noEvents) + 1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample42)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var41]);
				}
			);

		if(!state.fixedFlag$sample57)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, state.bias[var55]);
				}
			);

		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							state.st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.initialState], state.noStates);
				}
			);

		if(!state.fixedFlag$sample126)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = state.st[i$var104];
							for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample42)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, state.noStates, state.m[var41]);
				}
			);

		if(!state.fixedFlag$sample57)
			parallelFor(state.RNG$, 0, state.noStates, 1,
				(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v2, state.noEvents, state.bias[var55]);
				}
			);

		if(!state.fixedFlag$sample78)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.weights);
		if(!state.fixedFlag$sample80)
			state.initialState = DistributionSampling.sampleCategorical(state.RNG$, state.weights, state.noStates);
		if(!state.fixedFlag$sample95)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
							state.st[i$var87][0] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.initialState], state.noStates);
				}
			);

		if(!state.fixedFlag$sample126)
			parallelFor(state.RNG$, 0, state.samples, 1,
				(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
							int[] var116 = state.st[i$var104];
							for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
								var116[j$var115] = DistributionSampling.sampleCategorical(RNG$1, state.m[state.st[i$var104][(j$var115 - 1)]], state.noStates);
						}
				}
			);

	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample42)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
								inferSample42(var41, threadID$var41, RNG$1);
					}
				);

			if(!state.fixedFlag$sample57)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
								inferSample57(var55, threadID$var55, RNG$1);
					}
				);

			if(!state.fixedFlag$sample78)
				inferSample78();
			if(!state.fixedFlag$sample80)
				inferSample80();
			if(!state.fixedFlag$sample95)
				parallelFor(state.RNG$, 0, state.samples, 1,
					(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
								inferSample95(i$var87, threadID$i$var87, RNG$1);
					}
				);

			if(!state.fixedFlag$sample126)
				parallelFor(state.RNG$, 0, state.samples, 1,
					(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
								for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
									inferSample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
					}
				);

		} else {
			if(!state.fixedFlag$sample126)
				parallelFor(state.RNG$, 0, state.samples, 1,
					(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
								for(int j$var115 = (state.length$eventsMeasured[i$var104] - 1); j$var115 >= 1; j$var115 -= 1)
									inferSample126(i$var104, j$var115, threadID$i$var104, RNG$1);
							}
					}
				);

			if(!state.fixedFlag$sample95)
				parallelFor(state.RNG$, 0, state.samples, 1,
					(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1)
								inferSample95(i$var87, threadID$i$var87, RNG$1);
					}
				);

			if(!state.fixedFlag$sample80)
				inferSample80();
			if(!state.fixedFlag$sample78)
				inferSample78();
			if(!state.fixedFlag$sample57)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1)
								inferSample57(var55, threadID$var55, RNG$1);
					}
				);

			if(!state.fixedFlag$sample42)
				parallelFor(state.RNG$, 0, state.noStates, 1,
					(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1)
								inferSample42(var41, threadID$var41, RNG$1);
					}
				);

		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var41, int forEnd$var41, int threadID$var41, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var41 = forStart$var41; var41 < forEnd$var41; var41 += 1) {
						if(!state.constrainedFlag$sample42[var41])
							drawValueSample42(var41, threadID$var41, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.noStates, 1,
			(int forStart$var55, int forEnd$var55, int threadID$var55, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var55 = forStart$var55; var55 < forEnd$var55; var55 += 1) {
						if(!state.constrainedFlag$sample57[var55])
							drawValueSample57(var55, threadID$var55, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample78)
			drawValueSample78();
		if(!state.constrainedFlag$sample80)
			drawValueSample80();
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var87, int forEnd$i$var87, int threadID$i$var87, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var87 = forStart$i$var87; i$var87 < forEnd$i$var87; i$var87 += 1) {
						if(!state.constrainedFlag$sample95[i$var87])
							drawValueSample95(i$var87, threadID$i$var87, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$i$var104, int forEnd$i$var104, int threadID$i$var104, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var104 = forStart$i$var104; i$var104 < forEnd$i$var104; i$var104 += 1) {
						for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1) {
							if(!state.constrainedFlag$sample126[i$var104][(j$var115 - 1)])
								drawValueSample126(i$var104, j$var115, threadID$i$var104, RNG$1);
						}
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample42)
			state.logProbability$var42 = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample57)
			state.logProbability$var56 = Double.NaN;
		if(!state.fixedProbFlag$sample78)
			state.logProbability$weights = Double.NaN;
		if(!state.fixedProbFlag$sample80)
			state.logProbability$initialState = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample95) {
			for(int i$var87 = 0; i$var87 < state.samples; i$var87 += 1)
				state.logProbability$sample95[i$var87] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample126) {
			for(int i$var104 = 0; i$var104 < state.samples; i$var104 += 1) {
				for(int j$var115 = 1; j$var115 < state.length$eventsMeasured[i$var104]; j$var115 += 1)
					state.logProbability$sample126[i$var104][(j$var115 - 1)] = Double.NaN;
			}
		}
		state.logProbability$events = 0.0;
		if(!state.fixedProbFlag$sample159) {
			for(int i$var136 = 0; i$var136 < state.samples; i$var136 += 1) {
				for(int j$var149 = 1; j$var149 < state.length$eventsMeasured[i$var136]; j$var149 += 1)
					state.logProbability$sample159[i$var136][(j$var149 - 1)] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		for(int var14 = 0; var14 < state.noStates; var14 += 1)
			state.v[var14] = 0.1;
		for(int var27 = 0; var27 < state.noEvents; var27 += 1)
			state.v2[var27] = 0.1;
		state.samples = state.length$eventsMeasured.length;
		for(int index$constrainedFlag$sample95$1 = 0; index$constrainedFlag$sample95$1 < state.constrainedFlag$sample95.length; index$constrainedFlag$sample95$1 += 1)
			state.constrainedFlag$sample95[index$constrainedFlag$sample95$1] = true;
		for(int index$constrainedFlag$sample126$1 = 0; index$constrainedFlag$sample126$1 < state.constrainedFlag$sample126.length; index$constrainedFlag$sample126$1 += 1) {
			boolean[] cv$constrainedFlag$sample126$1 = state.constrainedFlag$sample126[index$constrainedFlag$sample126$1];
			for(int index$constrainedFlag$sample126$2 = 0; index$constrainedFlag$sample126$2 < cv$constrainedFlag$sample126$1.length; index$constrainedFlag$sample126$2 += 1)
				cv$constrainedFlag$sample126$1[index$constrainedFlag$sample126$2] = true;
		}
		for(int index$constrainedFlag$sample42$1 = 0; index$constrainedFlag$sample42$1 < state.constrainedFlag$sample42.length; index$constrainedFlag$sample42$1 += 1)
			state.constrainedFlag$sample42[index$constrainedFlag$sample42$1] = true;
		for(int index$constrainedFlag$sample57$1 = 0; index$constrainedFlag$sample57$1 < state.constrainedFlag$sample57.length; index$constrainedFlag$sample57$1 += 1)
			state.constrainedFlag$sample57[index$constrainedFlag$sample57$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample42)
			logProbabilityValue$sample42();
		if(state.fixedFlag$sample57)
			logProbabilityValue$sample57();
		if(state.fixedFlag$sample78)
			logProbabilityValue$sample78();
		if(state.fixedFlag$sample80)
			logProbabilityValue$sample80();
		logProbabilityValue$sample159();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityDistribution$sample95();
		logProbabilityDistribution$sample126();
		logProbabilityDistribution$sample159();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample42();
		logProbabilityValue$sample57();
		logProbabilityValue$sample78();
		logProbabilityValue$sample80();
		logProbabilityValue$sample95();
		logProbabilityValue$sample126();
		logProbabilityValue$sample159();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.events.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			int[] cv$source2 = state.eventsMeasured[cv$index1];
			int[] cv$target2 = state.events[cv$index1];
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
		     + "model HMM_Mk2Dist(int[][] eventsMeasured, int noStates, int noEvents) {\n"
		     + "        \n"
		     + "        // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "        double[] v = new double[noStates] <~ 0.1;\n"
		     + "        double[] v2 = new double[noEvents] <~ 0.1;\n"
		     + "        double[][] m = dirichlet(v).sample(noStates);\n"
		     + "        \n"
		     + "        // Construct the bias for each webpage.\n"
		     + "        double[][] bias = dirichlet(v2).sample(noStates);\n"
		     + "\n"
		     + "        // Determine how many samples the model will need to produce.\n"
		     + "        int samples = eventsMeasured.length;\n"
		     + "        \n"
		     + "        // Allocate space for the state, i.e. which webpage we are going to trigger an event on.\n"
		     + "        int[][] st = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            st[i] = new int[streamLength];\n"
		     + "        }\n"
		     + "\n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        double[] weights = dirichlet(v).sample();\n"
		     + "        int initialState = categorical(weights).sample();\n"
		     + "        for(int i:[0..samples)) {\n"
		     + "            st[i][0] = categorical(m[initialState]).sampleDistribution();\n"
		     + "        }\n"
		     + "\n"
		     + "        //Determine the remaining states based on the previous state.\n"
		     + "        for(int i:[0 .. samples)){\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                st[i][j] = categorical(m[st[i][j-1]]).sampleDistribution();\n"
		     + "            }\n"
		     + "        }\n"
		     + "            \n"
		     + "        //Generate each event.\n"
		     + "        int[][] events = new int[samples][];\n"
		     + "        for(int i:[0 .. samples)) {\n"
		     + "            int streamLength = eventsMeasured[i].length;\n"
		     + "            events[i] = new int[streamLength];\n"
		     + "            for(int j:[1..streamLength)){\n"
		     + "                events[i][j] = categorical(bias[st[i][j]]).sample() + 1;\n"
		     + "            }\n"
		     + "        }\n"
		     + "\n"
		     + "        //Tie the values of the flips to the values we have measured.\n"
		     + "        events.observe(eventsMeasured);\n"
		     + "}\n"
		     + "";
	}
}