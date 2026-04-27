package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMMetrics2$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMMetrics2.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMMetrics2$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$distributionAccumulator$var120;
		double[] cv$var102$stateProbabilityGlobal;
		double[] cv$var121$stateProbabilityGlobal;
		double[] cv$var19$countGlobal;
		double[] cv$var32$countGlobal;
		boolean[][] guard$sample104gaussian156$global;
		boolean[][] guard$sample123gaussian156$global;

		@Override
		public final void allocateScratch() {
			cv$var19$countGlobal = new double[state.noStates];
			cv$var32$countGlobal = new double[state.noStates];
			cv$distributionAccumulator$var120 = new double[state.noStates];
			cv$var102$stateProbabilityGlobal = new double[state.noStates];
			{
				int cv$max_timeStep$var136 = 0;
				for(int sample = 0; sample < state.length$metric.length; sample += 1)
					cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, state.length$metric[sample]);
				guard$sample104gaussian156$global = new boolean[state.length$metric.length][cv$max_timeStep$var136];
			}
			cv$var121$stateProbabilityGlobal = new double[state.noStates];
			int cv$max_timeStep$var136 = 0;
			for(int sample = 0; sample < state.length$metric.length; sample += 1)
				cv$max_timeStep$var136 = Math.max(cv$max_timeStep$var136, state.length$metric[sample]);
			guard$sample123gaussian156$global = new boolean[state.length$metric.length][cv$max_timeStep$var136];
		}
	}


	public HMMMetrics2$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample104(int sample) {
		state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
	}

	private final void drawValueSample123(int sample, int timeStep$var113) {
		state.st[sample][timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
	}

	private final void drawValueSample19() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
	}

	private final void drawValueSample32(int var31) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
	}

	private final void drawValueSample52(int var50) {
		state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
	}

	private final void drawValueSample68(int var66) {
		state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample84(int var82) {
		state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample104(int sample) {
		state.constrainedFlag$sample104[sample] = false;
		int cv$numStates = Math.max(0, state.noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.noStates) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$valuePos])) && (state.initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(state.initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((state.fixedFlag$sample123 && (1 < state.length$metric[sample]))) {
				state.constrainedFlag$sample104[sample] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < state.noStates)) {
					double[] var119 = state.m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((((0.0 <= state.st[sample][1]) && (state.st[sample][1] < state.noStates)) && (0.0 <= var119[state.st[sample][1]])) && (var119[state.st[sample][1]] <= 1.0))?Math.log(var119[state.st[sample][1]]):Double.NEGATIVE_INFINITY);
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
			if((0 < state.length$metric[sample])) {
				{
					state.constrainedFlag$sample104[sample] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < state.noStates)) {
						double var139 = state.metric_valid_bias[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][0]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
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
				if(state.metric_valid_g[sample][0]) {
					scratch.guard$sample104gaussian156$global[sample][0] = false;
					if(!scratch.guard$sample104gaussian156$global[sample][0]) {
						scratch.guard$sample104gaussian156$global[sample][0] = true;
						state.constrainedFlag$sample104[sample] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < state.noStates)) {
							double var149 = state.metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
					if(!scratch.guard$sample104gaussian156$global[sample][0]) {
						scratch.guard$sample104gaussian156$global[sample][0] = true;
						state.constrainedFlag$sample104[sample] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < state.noStates)) {
							double var149 = state.metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
				}
			}
			if((!state.fixedFlag$sample123 && (1 < state.length$metric[sample]))) {
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					scratch.cv$distributionAccumulator$var120[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < state.noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var120, 1.0, state.m[cv$valuePos], state.noStates);
				}
				double[] cv$sampleDistribution = state.distribution$sample123[sample][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var120[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			scratch.cv$var102$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample104[sample]) {
			double[] cv$localProbability = state.distribution$sample104[sample];
			double cv$logSum;
			double cv$lseMax = scratch.cv$var102$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var102$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var102$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var102$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var102$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample123(int sample, int timeStep$var113) {
		state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = false;
		int cv$numStates = 0;
		if((1 == timeStep$var113)) {
			if(state.fixedFlag$sample104) {
				int var31 = state.st[sample][0];
				if(((0 <= var31) && (var31 < state.noStates)))
					cv$numStates = Math.max(0, state.noStates);
			} else {
				if((0 < state.noStates))
					cv$numStates = state.noStates;
			}
		}
		if((0 < state.noStates)) {
			int index$timeStep$13 = (timeStep$var113 - 1);
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var113)))
				cv$numStates = state.noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var113)) {
				if(state.fixedFlag$sample104) {
					int var31 = state.st[sample][0];
					if(((0 <= var31) && (var31 < state.noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var119 = state.m[state.st[sample][0]];
						double cv$accumulatedProbabilities = ((((cv$valuePos < state.noStates) && (0.0 <= var119[cv$valuePos])) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < state.length$metric[sample])) {
							{
								state.constrainedFlag$sample123[sample][0] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < state.noStates)) {
									double var139 = state.metric_valid_bias[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][1]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
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
							if(state.metric_valid_g[sample][1]) {
								scratch.guard$sample123gaussian156$global[sample][1] = false;
								if(!scratch.guard$sample123gaussian156$global[sample][1]) {
									scratch.guard$sample123gaussian156$global[sample][1] = true;
									state.constrainedFlag$sample123[sample][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < state.noStates)) {
										double var149 = state.metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][1] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
								if(!scratch.guard$sample123gaussian156$global[sample][1]) {
									scratch.guard$sample123gaussian156$global[sample][1] = true;
									state.constrainedFlag$sample123[sample][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < state.noStates)) {
										double var149 = state.metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][1] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
							}
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample104$22 = 0; index$sample104$22 < state.noStates; index$sample104$22 += 1) {
						double cv$probabilitySample104Value23 = state.distribution$sample104[sample][index$sample104$22];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample104Value23);
						double[] var119 = state.m[index$sample104$22];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample104Value23) + (((0.0 <= var119[cv$valuePos]) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < state.length$metric[sample])) {
							state.constrainedFlag$sample123[sample][0] = true;
							double var139 = state.metric_valid_bias[cv$valuePos];
							cv$accumulatedProbabilities = ((((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][1]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
							if(state.metric_valid_g[sample][1]) {
								scratch.guard$sample123gaussian156$global[sample][1] = false;
								if(!scratch.guard$sample123gaussian156$global[sample][1]) {
									scratch.guard$sample123gaussian156$global[sample][1] = true;
									state.constrainedFlag$sample123[sample][0] = true;
									double var149 = state.metric_var[cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][1] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
								}
								if(!scratch.guard$sample123gaussian156$global[sample][1]) {
									scratch.guard$sample123gaussian156$global[sample][1] = true;
									state.constrainedFlag$sample123[sample][0] = true;
									double var149 = state.metric_var[cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][1] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				}
			}
			int index$timeStep$30 = (timeStep$var113 - 1);
			if(((1 <= index$timeStep$30) && !(index$timeStep$30 == timeStep$var113))) {
				for(int index$sample123$31 = 0; index$sample123$31 < state.noStates; index$sample123$31 += 1) {
					double cv$probabilitySample123Value32 = state.distribution$sample123[sample][(index$timeStep$30 - 1)][index$sample123$31];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample123Value32);
					double[] var119 = state.m[index$sample123$31];
					state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
					double var139 = state.metric_valid_bias[index$sample123$31];
					double cv$accumulatedProbabilities = (((((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][timeStep$var113]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample123Value32)) + (((0.0 <= var119[cv$valuePos]) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY));
					if(state.metric_valid_g[sample][timeStep$var113]) {
						scratch.guard$sample123gaussian156$global[sample][timeStep$var113] = false;
						if(!scratch.guard$sample123gaussian156$global[sample][timeStep$var113]) {
							scratch.guard$sample123gaussian156$global[sample][timeStep$var113] = true;
							state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
							double var149 = state.metric_var[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var113] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!scratch.guard$sample123gaussian156$global[sample][timeStep$var113]) {
							scratch.guard$sample123gaussian156$global[sample][timeStep$var113] = true;
							state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
							double var149 = state.metric_var[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var113] - state.metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			}
			int index$timeStep$265_3 = (timeStep$var113 + 1);
			if((index$timeStep$265_3 < state.length$metric[sample])) {
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1)
					scratch.cv$distributionAccumulator$var120[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < state.noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == timeStep$var113)) {
						if(state.fixedFlag$sample104) {
							int index$var31$276_1 = state.st[sample][0];
							if(((0 <= index$var31$276_1) && (index$var31$276_1 < state.noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample104$272 = 0; index$sample104$272 < state.noStates; index$sample104$272 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample104[sample][index$sample104$272]);
						}
					}
					int index$timeStep$280 = (timeStep$var113 - 1);
					if((((1 <= index$timeStep$280) && !(index$timeStep$280 == timeStep$var113)) && !(index$timeStep$280 == index$timeStep$265_3))) {
						for(int index$sample123$281 = 0; index$sample123$281 < state.noStates; index$sample123$281 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample123[sample][(index$timeStep$280 - 1)][index$sample123$281]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var120, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], state.noStates);
				}
				double[] cv$sampleDistribution = state.distribution$sample123[sample][(index$timeStep$265_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < state.noStates; cv$i += 1) {
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var120[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			scratch.cv$var121$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)]) {
			double[] cv$localProbability = state.distribution$sample123[sample][(timeStep$var113 - 1)];
			double cv$logSum;
			double cv$lseMax = scratch.cv$var121$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var121$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var121$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var121$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var121$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void inferSample19() {
		state.constrainedFlag$sample19 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			scratch.cv$var19$countGlobal[cv$loopIndex] = 0.0;
		if(state.fixedFlag$sample104) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				state.constrainedFlag$sample19 = true;
				scratch.cv$var19$countGlobal[state.st[sample][0]] = (scratch.cv$var19$countGlobal[state.st[sample][0]] + 1.0);
			}
		} else {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
					scratch.cv$var19$countGlobal[cv$loopIndex] = (scratch.cv$var19$countGlobal[cv$loopIndex] + state.distribution$sample104[sample][cv$loopIndex]);
			}
		}
		if(state.constrainedFlag$sample19)
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var19$countGlobal, state.initialStateDistribution, state.noStates);
	}

	private final void inferSample32(int var31) {
		state.constrainedFlag$sample32[var31] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
			scratch.cv$var32$countGlobal[cv$loopIndex] = 0.0;
		if(state.fixedFlag$sample123) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				if((1 < state.length$metric[sample])) {
					if(state.fixedFlag$sample104) {
						if((var31 == state.st[sample][0])) {
							state.constrainedFlag$sample32[var31] = true;
							scratch.cv$var32$countGlobal[state.st[sample][1]] = (scratch.cv$var32$countGlobal[state.st[sample][1]] + 1.0);
						}
					} else {
						state.constrainedFlag$sample32[var31] = true;
						scratch.cv$var32$countGlobal[state.st[sample][1]] = (scratch.cv$var32$countGlobal[state.st[sample][1]] + state.distribution$sample104[sample][var31]);
					}
				}
			}
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 2; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					if((var31 == state.st[sample][(timeStep$var113 - 1)])) {
						state.constrainedFlag$sample32[var31] = true;
						scratch.cv$var32$countGlobal[state.st[sample][timeStep$var113]] = (scratch.cv$var32$countGlobal[state.st[sample][timeStep$var113]] + 1.0);
					}
				}
			}
		} else {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				if((1 < state.length$metric[sample])) {
					if(state.fixedFlag$sample104) {
						if((var31 == state.st[sample][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
								scratch.cv$var32$countGlobal[cv$loopIndex] = (scratch.cv$var32$countGlobal[cv$loopIndex] + state.distribution$sample123[sample][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = state.distribution$sample104[sample][var31];
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
							scratch.cv$var32$countGlobal[cv$loopIndex] = (scratch.cv$var32$countGlobal[cv$loopIndex] + (state.distribution$sample123[sample][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					int index$timeStep$52 = (timeStep$var113 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = state.distribution$sample123[sample][(index$timeStep$52 - 1)][var31];
						for(int cv$loopIndex = 0; cv$loopIndex < state.noStates; cv$loopIndex += 1)
							scratch.cv$var32$countGlobal[cv$loopIndex] = (scratch.cv$var32$countGlobal[cv$loopIndex] + (state.distribution$sample123[sample][(timeStep$var113 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(state.constrainedFlag$sample32[var31])
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var32$countGlobal, state.m[var31], state.noStates);
	}

	private final void inferSample52(int var50) {
		state.constrainedFlag$sample52[var50] = false;
		double cv$originalValue = state.metric_mean[var50];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				if(((0 < state.length$metric[sample]) && state.metric_valid_g[sample][0])) {
					if(state.fixedFlag$sample104) {
						if((var50 == state.st[sample][0])) {
							state.constrainedFlag$sample52[var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = state.st[sample][0];
							if(((0 <= var66) && (var66 < state.noStates))) {
								double var149 = state.metric_var[state.st[sample][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						double cv$probabilitySample104Value7 = state.distribution$sample104[sample][var50];
						state.constrainedFlag$sample52[var50] = true;
						double var149 = state.metric_var[var50];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						if(state.fixedFlag$sample123) {
							if((var50 == state.st[sample][timeStep$var136])) {
								state.constrainedFlag$sample52[var50] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var66 = state.st[sample][timeStep$var136];
								if(((0 <= var66) && (var66 < state.noStates))) {
									double var149 = state.metric_var[state.st[sample][timeStep$var136]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
						} else {
							double cv$probabilitySample123Value18 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var50];
							state.constrainedFlag$sample52[var50] = true;
							double var149 = state.metric_var[var50];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample52[var50]) {
			state.metric_mean[var50] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				if(((0 < state.length$metric[sample]) && state.metric_valid_g[sample][0])) {
					if(state.fixedFlag$sample104) {
						if((var50 == state.st[sample][0])) {
							state.constrainedFlag$sample52[var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = state.st[sample][0];
							if(((0 <= var66) && (var66 < state.noStates))) {
								double var149 = state.metric_var[state.st[sample][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						double cv$probabilitySample104Value7 = state.distribution$sample104[sample][var50];
						state.constrainedFlag$sample52[var50] = true;
						double var149 = state.metric_var[var50];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						if(state.fixedFlag$sample123) {
							if((var50 == state.st[sample][timeStep$var136])) {
								state.constrainedFlag$sample52[var50] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var66 = state.st[sample][timeStep$var136];
								if(((0 <= var66) && (var66 < state.noStates))) {
									double var149 = state.metric_var[state.st[sample][timeStep$var136]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
						} else {
							double cv$probabilitySample123Value18 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var50];
							state.constrainedFlag$sample52[var50] = true;
							double var149 = state.metric_var[var50];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.metric_mean[var50] = cv$originalValue;
		}
	}

	private final void inferSample68(int var66) {
		state.constrainedFlag$sample68[var66] = false;
		double cv$originalValue = state.metric_var[var66];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				if(((0 < state.length$metric[sample]) && state.metric_valid_g[sample][0])) {
					if(state.fixedFlag$sample104) {
						if((var66 == state.st[sample][0])) {
							state.constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = state.st[sample][0];
							if(((0 <= var50) && (var50 < state.noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[state.st[sample][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						double cv$probabilitySample104Value7 = state.distribution$sample104[sample][var66];
						state.constrainedFlag$sample68[var66] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[var66]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						if(state.fixedFlag$sample123) {
							if((var66 == state.st[sample][timeStep$var136])) {
								state.constrainedFlag$sample68[var66] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var50 = state.st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < state.noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[state.st[sample][timeStep$var136]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
						} else {
							double cv$probabilitySample123Value18 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var66];
							state.constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[var66]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample68[var66]) {
			state.metric_var[var66] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				if(((0 < state.length$metric[sample]) && state.metric_valid_g[sample][0])) {
					if(state.fixedFlag$sample104) {
						if((var66 == state.st[sample][0])) {
							state.constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = state.st[sample][0];
							if(((0 <= var50) && (var50 < state.noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[state.st[sample][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						double cv$probabilitySample104Value7 = state.distribution$sample104[sample][var66];
						state.constrainedFlag$sample68[var66] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][0] - state.metric_mean[var66]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						if(state.fixedFlag$sample123) {
							if((var66 == state.st[sample][timeStep$var136])) {
								state.constrainedFlag$sample68[var66] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var50 = state.st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < state.noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[state.st[sample][timeStep$var136]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
						} else {
							double cv$probabilitySample123Value18 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var66];
							state.constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[var66]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
				state.metric_var[var66] = cv$originalValue;
		}
	}

	private final void inferSample84(int var82) {
		state.constrainedFlag$sample84[var82] = false;
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if((0 < state.length$metric[sample])) {
				if(state.fixedFlag$sample104) {
					if((var82 == state.st[sample][0])) {
						state.constrainedFlag$sample84[var82] = true;
						cv$count = (cv$count + 1.0);
						if(state.metric_valid_g[sample][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample104Value6 = state.distribution$sample104[sample][var82];
					state.constrainedFlag$sample84[var82] = true;
					cv$count = (cv$count + cv$probabilitySample104Value6);
					if(state.metric_valid_g[sample][0])
						cv$sum = (cv$sum + cv$probabilitySample104Value6);
				}
			}
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			for(int timeStep$var136 = 1; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
				if(state.fixedFlag$sample123) {
					if((var82 == state.st[sample][timeStep$var136])) {
						state.constrainedFlag$sample84[var82] = true;
						cv$count = (cv$count + 1.0);
						if(state.metric_valid_g[sample][timeStep$var136])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample123Value17 = state.distribution$sample123[sample][(timeStep$var136 - 1)][var82];
					state.constrainedFlag$sample84[var82] = true;
					cv$count = (cv$count + cv$probabilitySample123Value17);
					if(state.metric_valid_g[sample][timeStep$var136])
						cv$sum = (cv$sum + cv$probabilitySample123Value17);
				}
			}
		}
		if(state.constrainedFlag$sample84[var82])
			state.metric_valid_bias[var82] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityDistribution$sample104() {
		if(!state.fixedProbFlag$sample104) {
			if(state.fixedFlag$sample104) {
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < state.noSamples; sample += 1) {
					int cv$sampleValue = state.st[sample][0];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample104[sample] = cv$distributionAccumulator;
				}
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample104 = state.fixedFlag$sample19;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample104[sample]);
			if(state.fixedFlag$sample104)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample104)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample123() {
		if(!state.fixedProbFlag$sample123) {
			if(state.fixedFlag$sample123) {
				double cv$accumulator = 0.0;
				for(int sample = 0; sample < state.noSamples; sample += 1) {
					for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						int cv$sampleValue = state.st[sample][timeStep$var113];
						if((1 == timeStep$var113)) {
							if(state.fixedFlag$sample104) {
								int var31 = state.st[sample][0];
								if(((0 <= var31) && (var31 < state.noStates))) {
									double[] var119 = state.m[state.st[sample][0]];
									cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
									cv$probabilityReached = 1.0;
								}
							} else {
								for(int index$sample104$6 = 0; index$sample104$6 < state.noStates; index$sample104$6 += 1) {
									double cv$probabilitySample104Value7 = state.distribution$sample104[sample][index$sample104$6];
									double[] var119 = state.m[index$sample104$6];
									double cv$weightedProbability = (Math.log(cv$probabilitySample104Value7) + (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value7);
								}
							}
						}
						if((2 <= timeStep$var113)) {
							int var31 = state.st[sample][(timeStep$var113 - 1)];
							if(((0 <= var31) && (var31 < state.noStates))) {
								double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
								double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
						state.logProbability$sample123[sample][(timeStep$var113 - 1)] = cv$distributionAccumulator;
					}
				}
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample123 = (state.fixedFlag$sample32 && state.fixedFlag$sample104);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample123[sample][(timeStep$var113 - 1)]);
			}
			if(state.fixedFlag$sample123)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample123)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample145() {
		if(!state.fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					boolean cv$sampleValue = state.metric_valid_g[sample][timeStep$var136];
					if((0 == timeStep$var136)) {
						if(state.fixedFlag$sample104) {
							int var82 = state.st[sample][0];
							if(((0 <= var82) && (var82 < state.noStates))) {
								double var139 = state.metric_valid_bias[state.st[sample][0]];
								cv$distributionAccumulator = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
								cv$probabilityReached = 1.0;
							}
						} else {
							for(int index$sample104$4 = 0; index$sample104$4 < state.noStates; index$sample104$4 += 1) {
								double cv$probabilitySample104Value5 = state.distribution$sample104[sample][index$sample104$4];
								double var139 = state.metric_valid_bias[index$sample104$4];
								double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
							}
						}
					}
					if((1 <= timeStep$var136)) {
						if(state.fixedFlag$sample123) {
							int var82 = state.st[sample][timeStep$var136];
							if(((0 <= var82) && (var82 < state.noStates))) {
								double var139 = state.metric_valid_bias[state.st[sample][timeStep$var136]];
								double cv$weightedProbability = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
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
						} else {
							for(int index$sample123$13 = 0; index$sample123$13 < state.noStates; index$sample123$13 += 1) {
								double cv$probabilitySample123Value14 = state.distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$13];
								double var139 = state.metric_valid_bias[index$sample123$13];
								double cv$weightedProbability = (Math.log(cv$probabilitySample123Value14) + (((0.0 <= var139) && (var139 <= 1.0))?Math.log((cv$sampleValue?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY));
								if((cv$weightedProbability < cv$distributionAccumulator))
									cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
								else {
									if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
										cv$distributionAccumulator = cv$weightedProbability;
									else
										cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
								}
								cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value14);
							}
						}
					}
					if((cv$probabilityReached == 0.0))
						cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					else
						cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample145[sample][timeStep$var136] = cv$distributionAccumulator;
				}
			}
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample145 = ((state.fixedFlag$sample84 && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample145[sample][timeStep$var136]);
			}
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample157() {
		if(!state.fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						double cv$probabilityReached = 0.0;
						double cv$sampleValue = state.var151[sample][timeStep$var136];
						if((0 == timeStep$var136)) {
							if(state.fixedFlag$sample104) {
								if((0 <= state.st[sample][0])) {
									int var50 = state.st[sample][0];
									if(((0 <= var50) && (var50 < state.noStates))) {
										double var149 = state.metric_var[state.st[sample][0]];
										cv$distributionAccumulator = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.metric_mean[state.st[sample][0]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
										cv$probabilityReached = 1.0;
									}
								}
							} else {
								for(int index$sample104$4 = 0; index$sample104$4 < state.noStates; index$sample104$4 += 1) {
									double cv$probabilitySample104Value5 = state.distribution$sample104[sample][index$sample104$4];
									double var149 = state.metric_var[index$sample104$4];
									double cv$weightedProbability = (Math.log(cv$probabilitySample104Value5) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.metric_mean[index$sample104$4]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample104Value5);
								}
							}
						}
						if((1 <= timeStep$var136)) {
							if(state.fixedFlag$sample123) {
								if((0 <= state.st[sample][timeStep$var136])) {
									int var50 = state.st[sample][timeStep$var136];
									if(((0 <= var50) && (var50 < state.noStates))) {
										double var149 = state.metric_var[state.st[sample][timeStep$var136]];
										double cv$weightedProbability = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.metric_mean[state.st[sample][timeStep$var136]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
							} else {
								for(int index$sample123$49 = 0; index$sample123$49 < state.noStates; index$sample123$49 += 1) {
									double cv$probabilitySample123Value50 = state.distribution$sample123[sample][(timeStep$var136 - 1)][index$sample123$49];
									double var149 = state.metric_var[index$sample123$49];
									double cv$weightedProbability = (Math.log(cv$probabilitySample123Value50) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((cv$sampleValue - state.metric_mean[index$sample123$49]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
									if((cv$weightedProbability < cv$distributionAccumulator))
										cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
									else {
										if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
											cv$distributionAccumulator = cv$weightedProbability;
										else
											cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
									}
									cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample123Value50);
								}
							}
						}
						if((cv$probabilityReached == 0.0))
							cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
						else
							cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						state.logProbability$sample157[sample][timeStep$var136] = cv$distributionAccumulator;
					}
				}
			}
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample157 = (((state.fixedFlag$sample52 && state.fixedFlag$sample68) && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136])
						cv$accumulator = (cv$accumulator + state.logProbability$sample157[sample][timeStep$var136]);
				}
			}
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample104() {
		if(!state.fixedProbFlag$sample104) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				int cv$sampleValue = state.st[sample][0];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= state.initialStateDistribution[cv$sampleValue])) && (state.initialStateDistribution[cv$sampleValue] <= 1.0))?Math.log(state.initialStateDistribution[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample104[sample] = cv$distributionAccumulator;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample104)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample104 = (state.fixedFlag$sample104 && state.fixedFlag$sample19);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample104[sample]);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample104)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample123() {
		if(!state.fixedProbFlag$sample123) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					int cv$sampleValue = state.st[sample][timeStep$var113];
					double[] var119 = state.m[state.st[sample][(timeStep$var113 - 1)]];
					double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noStates)) && (0 < state.noStates)) && (0.0 <= var119[cv$sampleValue])) && (var119[cv$sampleValue] <= 1.0))?Math.log(var119[cv$sampleValue]):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample123[sample][(timeStep$var113 - 1)] = cv$distributionAccumulator;
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample123)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample123 = ((state.fixedFlag$sample123 && state.fixedFlag$sample32) && state.fixedFlag$sample104);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample123[sample][(timeStep$var113 - 1)]);
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample123)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample145() {
		if(!state.fixedProbFlag$sample145) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					double var139 = state.metric_valid_bias[state.st[sample][timeStep$var136]];
					double cv$distributionAccumulator = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((state.metric_valid_g[sample][timeStep$var136]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
					cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
					state.logProbability$sample145[sample][timeStep$var136] = cv$distributionAccumulator;
				}
			}
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample145 = ((state.fixedFlag$sample84 && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					cv$accumulator = (cv$accumulator + state.logProbability$sample145[sample][timeStep$var136]);
			}
			state.logProbability$metric_valid_1d = (state.logProbability$metric_valid_1d + cv$accumulator);
			state.logProbability$metric_valid_g = (state.logProbability$metric_valid_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample157() {
		if(!state.fixedProbFlag$sample157) {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136]) {
						double var149 = state.metric_var[state.st[sample][timeStep$var136]];
						double cv$distributionAccumulator = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((state.var151[sample][timeStep$var136] - state.metric_mean[state.st[sample][timeStep$var136]]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
						cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
						state.logProbability$sample157[sample][timeStep$var136] = cv$distributionAccumulator;
					}
				}
			}
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample157 = (((state.fixedFlag$sample52 && state.fixedFlag$sample68) && state.fixedFlag$sample104) && state.fixedFlag$sample123);
		} else {
			double cv$accumulator = 0.0;
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
					if(state.metric_valid_g[sample][timeStep$var136])
						cv$accumulator = (cv$accumulator + state.logProbability$sample157[sample][timeStep$var136]);
				}
			}
			state.logProbability$var151 = (state.logProbability$var151 + cv$accumulator);
			state.logProbability$metric_g = (state.logProbability$metric_g + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample19() {
		if(!state.fixedProbFlag$sample19) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.initialStateDistribution, state.v, state.noStates);
			state.logProbability$initialStateDistribution = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample19)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample19 = state.fixedFlag$sample19;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$initialStateDistribution);
			if(state.fixedFlag$sample19)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$initialStateDistribution);
		}
	}

	private final void logProbabilityValue$sample32() {
		if(!state.fixedProbFlag$sample32) {
			double cv$sampleAccumulator = 0.0;
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var31], state.v, state.noStates));
			state.logProbability$var32 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample32)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample32 = state.fixedFlag$sample32;
		} else {
			state.logProbability$m = (state.logProbability$m + state.logProbability$var32);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var32);
			if(state.fixedFlag$sample32)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var32);
		}
	}

	private final void logProbabilityValue$sample52() {
		if(!state.fixedProbFlag$sample52) {
			double cv$sampleAccumulator = 0.0;
			for(int var50 = 0; var50 < state.noStates; var50 += 1) {
				double cv$sampleValue = state.metric_mean[var50];
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= cv$sampleValue) && (cv$sampleValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY));
			}
			state.logProbability$var51 = cv$sampleAccumulator;
			state.logProbability$metric_mean = (state.logProbability$metric_mean + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample52 = state.fixedFlag$sample52;
		} else {
			state.logProbability$metric_mean = (state.logProbability$metric_mean + state.logProbability$var51);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var51);
			if(state.fixedFlag$sample52)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var51);
		}
	}

	private final void logProbabilityValue$sample68() {
		if(!state.fixedProbFlag$sample68) {
			double cv$sampleAccumulator = 0.0;
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityInverseGamma(state.metric_var[var66], 1.0, 1.0));
			state.logProbability$var67 = cv$sampleAccumulator;
			state.logProbability$metric_var = (state.logProbability$metric_var + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample68)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample68 = state.fixedFlag$sample68;
		} else {
			state.logProbability$metric_var = (state.logProbability$metric_var + state.logProbability$var67);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var67);
			if(state.fixedFlag$sample68)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var67);
		}
	}

	private final void logProbabilityValue$sample84() {
		if(!state.fixedProbFlag$sample84) {
			double cv$sampleAccumulator = 0.0;
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.metric_valid_bias[var82], 1.0, 1.0));
			state.logProbability$var83 = cv$sampleAccumulator;
			state.logProbability$metric_valid_bias = (state.logProbability$metric_valid_bias + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample84 = state.fixedFlag$sample84;
		} else {
			state.logProbability$metric_valid_bias = (state.logProbability$metric_valid_bias + state.logProbability$var83);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var83);
			if(state.fixedFlag$sample84)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var83);
		}
	}

<<<<<<< Renaming_functions
	private final void sample104(int sample) {
		constrainedFlag$sample104[sample] = false;
		int cv$numStates = Math.max(0, noStates);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			double cv$accumulatedProbabilities = (((((cv$valuePos < noStates) && (0 < noStates)) && (0.0 <= initialStateDistribution[cv$valuePos])) && (initialStateDistribution[cv$valuePos] <= 1.0))?Math.log(initialStateDistribution[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((fixedFlag$sample123 && (1 < length$metric[sample]))) {
				constrainedFlag$sample104[sample] = true;
				double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
				double cv$consumerDistributionProbabilityAccumulator = 1.0;
				if((cv$valuePos < noStates)) {
					double[] var119 = m[cv$valuePos];
					cv$accumulatedConsumerProbabilities = (((((0.0 <= st[sample][1]) && (st[sample][1] < noStates)) && (0.0 <= var119[st[sample][1]])) && (var119[st[sample][1]] <= 1.0))?Math.log(var119[st[sample][1]]):Double.NEGATIVE_INFINITY);
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
			if((0 < length$metric[sample])) {
				{
					constrainedFlag$sample104[sample] = true;
					double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
					double cv$consumerDistributionProbabilityAccumulator = 1.0;
					if((cv$valuePos < noStates)) {
						double var139 = metric_valid_bias[cv$valuePos];
						cv$accumulatedConsumerProbabilities = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[sample][0]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
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
				if(metric_valid_g[sample][0]) {
					guard$sample104gaussian156$global[sample][0] = false;
					if(!guard$sample104gaussian156$global[sample][0]) {
						guard$sample104gaussian156$global[sample][0] = true;
						constrainedFlag$sample104[sample] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double var149 = metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
					if(!guard$sample104gaussian156$global[sample][0]) {
						guard$sample104gaussian156$global[sample][0] = true;
						constrainedFlag$sample104[sample] = true;
						double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
						double cv$consumerDistributionProbabilityAccumulator = 1.0;
						if((cv$valuePos < noStates)) {
							double var149 = metric_var[cv$valuePos];
							cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
				}
			}
			if((!fixedFlag$sample123 && (1 < length$metric[sample]))) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var120[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					cv$reachedDistributionProbability = 1.0;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var120, 1.0, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample123[sample][0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var120[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var102$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(constrainedFlag$sample104[sample]) {
			double[] cv$localProbability = distribution$sample104[sample];
			double cv$logSum;
			double cv$lseMax = cv$var102$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var102$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var102$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$var102$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var102$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample123(int sample, int timeStep$var113) {
		constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = false;
		int cv$numStates = 0;
		if((1 == timeStep$var113)) {
			if(fixedFlag$sample104) {
				int var31 = st[sample][0];
				if(((0 <= var31) && (var31 < noStates)))
					cv$numStates = Math.max(0, noStates);
			} else {
				if((0 < noStates))
					cv$numStates = noStates;
			}
		}
		if((0 < noStates)) {
			int index$timeStep$13 = (timeStep$var113 - 1);
			if(((1 <= index$timeStep$13) && !(index$timeStep$13 == timeStep$var113)))
				cv$numStates = noStates;
		}
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == timeStep$var113)) {
				if(fixedFlag$sample104) {
					int var31 = st[sample][0];
					if(((0 <= var31) && (var31 < noStates))) {
						cv$reachedDistributionSourceRV = 1.0;
						double[] var119 = m[st[sample][0]];
						double cv$accumulatedProbabilities = ((((cv$valuePos < noStates) && (0.0 <= var119[cv$valuePos])) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY);
						if((1 < length$metric[sample])) {
							{
								constrainedFlag$sample123[sample][0] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								if((cv$valuePos < noStates)) {
									double var139 = metric_valid_bias[cv$valuePos];
									cv$accumulatedConsumerProbabilities = (((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[sample][1]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY);
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
							if(metric_valid_g[sample][1]) {
								guard$sample123gaussian156$global[sample][1] = false;
								if(!guard$sample123gaussian156$global[sample][1]) {
									guard$sample123gaussian156$global[sample][1] = true;
									constrainedFlag$sample123[sample][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double var149 = metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
								if(!guard$sample123gaussian156$global[sample][1]) {
									guard$sample123gaussian156$global[sample][1] = true;
									constrainedFlag$sample123[sample][0] = true;
									double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
									double cv$consumerDistributionProbabilityAccumulator = 1.0;
									if((cv$valuePos < noStates)) {
										double var149 = metric_var[cv$valuePos];
										cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
							}
						}
						cv$stateProbabilityValue = cv$accumulatedProbabilities;
					}
				} else {
					for(int index$sample104$22 = 0; index$sample104$22 < noStates; index$sample104$22 += 1) {
						double cv$probabilitySample104Value23 = distribution$sample104[sample][index$sample104$22];
						cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample104Value23);
						double[] var119 = m[index$sample104$22];
						double cv$accumulatedProbabilities = (Math.log(cv$probabilitySample104Value23) + (((0.0 <= var119[cv$valuePos]) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY));
						if((1 < length$metric[sample])) {
							constrainedFlag$sample123[sample][0] = true;
							double var139 = metric_valid_bias[cv$valuePos];
							cv$accumulatedProbabilities = ((((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[sample][1]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
							if(metric_valid_g[sample][1]) {
								guard$sample123gaussian156$global[sample][1] = false;
								if(!guard$sample123gaussian156$global[sample][1]) {
									guard$sample123gaussian156$global[sample][1] = true;
									constrainedFlag$sample123[sample][0] = true;
									double var149 = metric_var[cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
								}
								if(!guard$sample123gaussian156$global[sample][1]) {
									guard$sample123gaussian156$global[sample][1] = true;
									constrainedFlag$sample123[sample][0] = true;
									double var149 = metric_var[cv$valuePos];
									cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][1] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
				}
			}
			int index$timeStep$30 = (timeStep$var113 - 1);
			if(((1 <= index$timeStep$30) && !(index$timeStep$30 == timeStep$var113))) {
				for(int index$sample123$31 = 0; index$sample123$31 < noStates; index$sample123$31 += 1) {
					double cv$probabilitySample123Value32 = distribution$sample123[sample][(index$timeStep$30 - 1)][index$sample123$31];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample123Value32);
					double[] var119 = m[index$sample123$31];
					constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
					double var139 = metric_valid_bias[index$sample123$31];
					double cv$accumulatedProbabilities = (((((0.0 <= var139) && (var139 <= 1.0))?Math.log((metric_valid_g[sample][timeStep$var113]?var139:(1.0 - var139))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample123Value32)) + (((0.0 <= var119[cv$valuePos]) && (var119[cv$valuePos] <= 1.0))?Math.log(var119[cv$valuePos]):Double.NEGATIVE_INFINITY));
					if(metric_valid_g[sample][timeStep$var113]) {
						guard$sample123gaussian156$global[sample][timeStep$var113] = false;
						if(!guard$sample123gaussian156$global[sample][timeStep$var113]) {
							guard$sample123gaussian156$global[sample][timeStep$var113] = true;
							constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
							double var149 = metric_var[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var113] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
						}
						if(!guard$sample123gaussian156$global[sample][timeStep$var113]) {
							guard$sample123gaussian156$global[sample][timeStep$var113] = true;
							constrainedFlag$sample123[sample][(timeStep$var113 - 1)] = true;
							double var149 = metric_var[cv$valuePos];
							cv$accumulatedProbabilities = (((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var113] - metric_mean[cv$valuePos]) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			}
			int index$timeStep$265_3 = (timeStep$var113 + 1);
			if((index$timeStep$265_3 < length$metric[sample])) {
				for(int cv$i = 0; cv$i < noStates; cv$i += 1)
					cv$distributionAccumulator$var120[cv$i] = 0.0;
				double cv$reachedDistributionProbability = 0.0;
				if((cv$valuePos < noStates)) {
					double scopeVariable$reachedSourceProbability = 0.0;
					if((1 == timeStep$var113)) {
						if(fixedFlag$sample104) {
							int index$var31$276_1 = st[sample][0];
							if(((0 <= index$var31$276_1) && (index$var31$276_1 < noStates)))
								scopeVariable$reachedSourceProbability = 1.0;
						} else {
							for(int index$sample104$272 = 0; index$sample104$272 < noStates; index$sample104$272 += 1)
								scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample104[sample][index$sample104$272]);
						}
					}
					int index$timeStep$280 = (timeStep$var113 - 1);
					if((((1 <= index$timeStep$280) && !(index$timeStep$280 == timeStep$var113)) && !(index$timeStep$280 == index$timeStep$265_3))) {
						for(int index$sample123$281 = 0; index$sample123$281 < noStates; index$sample123$281 += 1)
							scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + distribution$sample123[sample][(index$timeStep$280 - 1)][index$sample123$281]);
					}
					cv$reachedDistributionProbability = scopeVariable$reachedSourceProbability;
					DistributionSampling.addProbabilityDistributionCategorical(cv$distributionAccumulator$var120, scopeVariable$reachedSourceProbability, m[cv$valuePos], noStates);
				}
				double[] cv$sampleDistribution = distribution$sample123[sample][(index$timeStep$265_3 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < noStates; cv$i += 1) {
					double cv$normalisedDistValue = (cv$distributionAccumulator$var120[cv$i] / cv$reachedDistributionProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * cv$reachedDistributionProbability) + 1.0) - Math.min(cv$reachedDistributionProbability, 1.0)));
			}
			cv$var121$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(constrainedFlag$sample123[sample][(timeStep$var113 - 1)]) {
			double[] cv$localProbability = distribution$sample123[sample][(timeStep$var113 - 1)];
			double cv$logSum;
			double cv$lseMax = cv$var121$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = cv$var121$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((cv$var121$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((cv$var121$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < cv$var121$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void sample19() {
		constrainedFlag$sample19 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var19$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample104) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				constrainedFlag$sample19 = true;
				cv$var19$countGlobal[st[sample][0]] = (cv$var19$countGlobal[st[sample][0]] + 1.0);
			}
		} else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
					cv$var19$countGlobal[cv$loopIndex] = (cv$var19$countGlobal[cv$loopIndex] + distribution$sample104[sample][cv$loopIndex]);
			}
		}
		if(constrainedFlag$sample19)
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var19$countGlobal, initialStateDistribution, noStates);
	}

	private final void sample32(int var31) {
		constrainedFlag$sample32[var31] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
			cv$var32$countGlobal[cv$loopIndex] = 0.0;
		if(fixedFlag$sample123) {
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample104) {
						if((var31 == st[sample][0])) {
							constrainedFlag$sample32[var31] = true;
							cv$var32$countGlobal[st[sample][1]] = (cv$var32$countGlobal[st[sample][1]] + 1.0);
						}
					} else {
						constrainedFlag$sample32[var31] = true;
						cv$var32$countGlobal[st[sample][1]] = (cv$var32$countGlobal[st[sample][1]] + distribution$sample104[sample][var31]);
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 2; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					if((var31 == st[sample][(timeStep$var113 - 1)])) {
						constrainedFlag$sample32[var31] = true;
						cv$var32$countGlobal[st[sample][timeStep$var113]] = (cv$var32$countGlobal[st[sample][timeStep$var113]] + 1.0);
					}
				}
			}
		} else {
			for(int sample = 0; sample < noSamples; sample += 1) {
				if((1 < length$metric[sample])) {
					if(fixedFlag$sample104) {
						if((var31 == st[sample][0])) {
							for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
								cv$var32$countGlobal[cv$loopIndex] = (cv$var32$countGlobal[cv$loopIndex] + distribution$sample123[sample][0][cv$loopIndex]);
						}
					} else {
						double cv$distributionProbability = distribution$sample104[sample][var31];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var32$countGlobal[cv$loopIndex] = (cv$var32$countGlobal[cv$loopIndex] + (distribution$sample123[sample][0][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < length$metric[sample]; timeStep$var113 += 1) {
					int index$timeStep$52 = (timeStep$var113 - 1);
					if((1 <= index$timeStep$52)) {
						double cv$distributionProbability = distribution$sample123[sample][(index$timeStep$52 - 1)][var31];
						for(int cv$loopIndex = 0; cv$loopIndex < noStates; cv$loopIndex += 1)
							cv$var32$countGlobal[cv$loopIndex] = (cv$var32$countGlobal[cv$loopIndex] + (distribution$sample123[sample][(timeStep$var113 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(constrainedFlag$sample32[var31])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, v, cv$var32$countGlobal, m[var31], noStates);
	}

	private final void sample52(int var50) {
		constrainedFlag$sample52[var50] = false;
		double cv$originalValue = metric_mean[var50];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (((0.0 <= cv$originalValue) && (cv$originalValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(((0 < length$metric[sample]) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						if((var50 == st[sample][0])) {
							constrainedFlag$sample52[var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = st[sample][0];
							if(((0 <= var66) && (var66 < noStates))) {
								double var149 = metric_var[st[sample][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						double cv$probabilitySample104Value7 = distribution$sample104[sample][var50];
						constrainedFlag$sample52[var50] = true;
						double var149 = metric_var[var50];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						if(fixedFlag$sample123) {
							if((var50 == st[sample][timeStep$var136])) {
								constrainedFlag$sample52[var50] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var66 = st[sample][timeStep$var136];
								if(((0 <= var66) && (var66 < noStates))) {
									double var149 = metric_var[st[sample][timeStep$var136]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
						} else {
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][var50];
							constrainedFlag$sample52[var50] = true;
							double var149 = metric_var[var50];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$originalValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample52[var50]) {
			metric_mean[var50] = cv$proposedValue;
			double cv$accumulatedProbabilities = (((0.0 <= cv$proposedValue) && (cv$proposedValue < 100.0))?-4.605170185988092:Double.NEGATIVE_INFINITY);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(((0 < length$metric[sample]) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						if((var50 == st[sample][0])) {
							constrainedFlag$sample52[var50] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var66 = st[sample][0];
							if(((0 <= var66) && (var66 < noStates))) {
								double var149 = metric_var[st[sample][0]];
								cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						double cv$probabilitySample104Value7 = distribution$sample104[sample][var50];
						constrainedFlag$sample52[var50] = true;
						double var149 = metric_var[var50];
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						if(fixedFlag$sample123) {
							if((var50 == st[sample][timeStep$var136])) {
								constrainedFlag$sample52[var50] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var66 = st[sample][timeStep$var136];
								if(((0 <= var66) && (var66 < noStates))) {
									double var149 = metric_var[st[sample][timeStep$var136]];
									cv$accumulatedConsumerProbabilities = ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY);
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
						} else {
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][var50];
							constrainedFlag$sample52[var50] = true;
							double var149 = metric_var[var50];
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < var149)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - cv$proposedValue) / Math.sqrt(var149))) - (Math.log(var149) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				metric_mean[var50] = cv$originalValue;
		}
	}

	private final void sample68(int var66) {
		constrainedFlag$sample68[var66] = false;
		double cv$originalValue = metric_var[var66];
		double cv$originalProbability;
		double cv$var = ((cv$originalValue * cv$originalValue) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$originalValue, 1.0, 1.0);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(((0 < length$metric[sample]) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						if((var66 == st[sample][0])) {
							constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = st[sample][0];
							if(((0 <= var50) && (var50 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						double cv$probabilitySample104Value7 = distribution$sample104[sample][var66];
						constrainedFlag$sample68[var66] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[var66]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						if(fixedFlag$sample123) {
							if((var66 == st[sample][timeStep$var136])) {
								constrainedFlag$sample68[var66] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var50 = st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
						} else {
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][var66];
							constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < cv$originalValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[var66]) / Math.sqrt(cv$originalValue))) - (Math.log(cv$originalValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(constrainedFlag$sample68[var66]) {
			metric_var[var66] = cv$proposedValue;
			double cv$accumulatedProbabilities = DistributionSampling.logProbabilityInverseGamma(cv$proposedValue, 1.0, 1.0);
			for(int sample = 0; sample < noSamples; sample += 1) {
				if(((0 < length$metric[sample]) && metric_valid_g[sample][0])) {
					if(fixedFlag$sample104) {
						if((var66 == st[sample][0])) {
							constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
							double cv$consumerDistributionProbabilityAccumulator = 1.0;
							int var50 = st[sample][0];
							if(((0 <= var50) && (var50 < noStates))) {
								cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[st[sample][0]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
					} else {
						double cv$probabilitySample104Value7 = distribution$sample104[sample][var66];
						constrainedFlag$sample68[var66] = true;
						double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample104Value7) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][0] - metric_mean[var66]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
						double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample104Value7), 0.0);
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
			for(int sample = 0; sample < noSamples; sample += 1) {
				for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
					if(metric_valid_g[sample][timeStep$var136]) {
						if(fixedFlag$sample123) {
							if((var66 == st[sample][timeStep$var136])) {
								constrainedFlag$sample68[var66] = true;
								double cv$accumulatedConsumerProbabilities = Double.NEGATIVE_INFINITY;
								double cv$consumerDistributionProbabilityAccumulator = 1.0;
								int var50 = st[sample][timeStep$var136];
								if(((0 <= var50) && (var50 < noStates))) {
									cv$accumulatedConsumerProbabilities = ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[st[sample][timeStep$var136]]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY);
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
						} else {
							double cv$probabilitySample123Value18 = distribution$sample123[sample][(timeStep$var136 - 1)][var66];
							constrainedFlag$sample68[var66] = true;
							double cv$accumulatedConsumerProbabilities = (Math.log(cv$probabilitySample123Value18) + ((0.0 < cv$proposedValue)?(DistributionSampling.logProbabilityGaussian(((var151[sample][timeStep$var136] - metric_mean[var66]) / Math.sqrt(cv$proposedValue))) - (Math.log(cv$proposedValue) * 0.5)):Double.NEGATIVE_INFINITY));
							double cv$consumerDistributionProbabilityAccumulator = Math.max((1.0 - cv$probabilitySample123Value18), 0.0);
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
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio)))
				metric_var[var66] = cv$originalValue;
		}
	}

	private final void sample84(int var82) {
		constrainedFlag$sample84[var82] = false;
		double cv$sum = 0.0;
		double cv$count = 0.0;
		for(int sample = 0; sample < noSamples; sample += 1) {
			if((0 < length$metric[sample])) {
				if(fixedFlag$sample104) {
					if((var82 == st[sample][0])) {
						constrainedFlag$sample84[var82] = true;
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample][0])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample104Value6 = distribution$sample104[sample][var82];
					constrainedFlag$sample84[var82] = true;
					cv$count = (cv$count + cv$probabilitySample104Value6);
					if(metric_valid_g[sample][0])
						cv$sum = (cv$sum + cv$probabilitySample104Value6);
				}
			}
		}
		for(int sample = 0; sample < noSamples; sample += 1) {
			for(int timeStep$var136 = 1; timeStep$var136 < length$metric[sample]; timeStep$var136 += 1) {
				if(fixedFlag$sample123) {
					if((var82 == st[sample][timeStep$var136])) {
						constrainedFlag$sample84[var82] = true;
						cv$count = (cv$count + 1.0);
						if(metric_valid_g[sample][timeStep$var136])
							cv$sum = (cv$sum + 1.0);
					}
				} else {
					double cv$probabilitySample123Value17 = distribution$sample123[sample][(timeStep$var136 - 1)][var82];
					constrainedFlag$sample84[var82] = true;
					cv$count = (cv$count + cv$probabilitySample123Value17);
					if(metric_valid_g[sample][timeStep$var136])
						cv$sum = (cv$sum + cv$probabilitySample123Value17);
				}
			}
		}
		if(constrainedFlag$sample84[var82])
			metric_valid_bias[var82] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

=======
>>>>>>> 36e6d3b Restructuring naming and renaming functions, this does result in them changing location in the generated source. This is done to allow the addition of unconstrained variables to be completed by adding in the methods to sample the unconstrained values.
	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.fixedFlag$sample104)
				state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			if(!state.fixedFlag$sample123) {
				int[] var114 = state.st[sample];
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
			}
			boolean[] metric_valid_1d = state.metric_valid_g[sample];
			double[] metric_1d = state.metric_g[sample];
			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
				metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(state.RNG$, state.metric_valid_bias[state.st[sample][timeStep$var136]]);
				if(metric_valid_1d[timeStep$var136]) {
					if(!state.fixedFlag$sample157)
						state.var151[sample][timeStep$var136] = ((Math.sqrt(state.metric_var[state.st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.metric_mean[state.st[sample][timeStep$var136]]);
					metric_1d[timeStep$var136] = state.var151[sample][timeStep$var136];
				}
			}
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.fixedFlag$sample104) {
				double[] cv$distribution$sample104 = state.distribution$sample104[sample];
				for(int index$var101 = 0; index$var101 < state.noStates; index$var101 += 1)
					cv$distribution$sample104[index$var101] = (((0.0 <= state.initialStateDistribution[index$var101]) && (state.initialStateDistribution[index$var101] <= 1.0))?state.initialStateDistribution[index$var101]:0.0);
			}
			if(!state.fixedFlag$sample123) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
					double[] cv$distribution$sample123 = state.distribution$sample123[sample][(timeStep$var113 - 1)];
					for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
						cv$distribution$sample123[index$var120] = 0.0;
					if((1 == timeStep$var113)) {
						if(state.fixedFlag$sample104) {
							int var31 = state.st[sample][0];
							if(((0 <= var31) && (var31 < state.noStates))) {
								double[] var119 = state.m[state.st[sample][0]];
								for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
									cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (((0.0 <= var119[index$var120]) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0));
							}
						} else {
							for(int index$sample104$3 = 0; index$sample104$3 < state.noStates; index$sample104$3 += 1) {
								double cv$probabilitySample104Value4 = state.distribution$sample104[sample][index$sample104$3];
								double[] var119 = state.m[index$sample104$3];
								for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
									cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample104Value4 * (((0.0 <= var119[index$var120]) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
							}
						}
					}
					int index$timeStep$11 = (timeStep$var113 - 1);
					if((1 <= index$timeStep$11)) {
						for(int index$sample123$12 = 0; index$sample123$12 < state.noStates; index$sample123$12 += 1) {
							double cv$probabilitySample123Value13 = state.distribution$sample123[sample][(index$timeStep$11 - 1)][index$sample123$12];
							double[] var119 = state.m[index$sample123$12];
							for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
								cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] + (cv$probabilitySample123Value13 * (((0.0 <= var119[index$var120]) && (var119[index$var120] <= 1.0))?var119[index$var120]:0.0)));
						}
					}
					double cv$var120$sum = 0.0;
					for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
						cv$var120$sum = (cv$var120$sum + cv$distribution$sample123[index$var120]);
					for(int index$var120 = 0; index$var120 < state.noStates; index$var120 += 1)
						cv$distribution$sample123[index$var120] = (cv$distribution$sample123[index$var120] / cv$var120$sum);
				}
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.fixedFlag$sample104)
				state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			if(!state.fixedFlag$sample123) {
				int[] var114 = state.st[sample];
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
			}
			boolean[] metric_valid_1d = state.metric_valid_g[sample];
			double[] metric_1d = state.metric_g[sample];
			for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1) {
				metric_valid_1d[timeStep$var136] = DistributionSampling.sampleBernoulli(state.RNG$, state.metric_valid_bias[state.st[sample][timeStep$var136]]);
				if(metric_valid_1d[timeStep$var136]) {
					if(!state.fixedFlag$sample157)
						state.var151[sample][timeStep$var136] = ((Math.sqrt(state.metric_var[state.st[sample][timeStep$var136]]) * DistributionSampling.sampleGaussian(state.RNG$)) + state.metric_mean[state.st[sample][timeStep$var136]]);
					metric_1d[timeStep$var136] = state.var151[sample][timeStep$var136];
				}
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.fixedFlag$sample104)
				state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			if(!state.fixedFlag$sample123) {
				int[] var114 = state.st[sample];
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample19)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.initialStateDistribution);
		if(!state.fixedFlag$sample32) {
			for(int var31 = 0; var31 < state.noStates; var31 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.noStates, state.m[var31]);
		}
		if(!state.fixedFlag$sample52) {
			for(int var50 = 0; var50 < state.noStates; var50 += 1)
				state.metric_mean[var50] = (DistributionSampling.sampleUniform(state.RNG$) * 100.0);
		}
		if(!state.fixedFlag$sample68) {
			for(int var66 = 0; var66 < state.noStates; var66 += 1)
				state.metric_var[var66] = DistributionSampling.sampleInverseGamma(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample84) {
			for(int var82 = 0; var82 < state.noStates; var82 += 1)
				state.metric_valid_bias[var82] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.fixedFlag$sample104)
				state.st[sample][0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialStateDistribution, state.noStates);
			if(!state.fixedFlag$sample123) {
				int[] var114 = state.st[sample];
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					var114[timeStep$var113] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[sample][(timeStep$var113 - 1)]], state.noStates);
			}
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample19)
				inferSample19();
			if(!state.fixedFlag$sample32) {
				for(int var31 = 0; var31 < state.noStates; var31 += 1)
					inferSample32(var31);
			}
			if(!state.fixedFlag$sample52) {
				for(int var50 = 0; var50 < state.noStates; var50 += 1)
					inferSample52(var50);
			}
			if(!state.fixedFlag$sample68) {
				for(int var66 = 0; var66 < state.noStates; var66 += 1)
					inferSample68(var66);
			}
			if(!state.fixedFlag$sample84) {
				for(int var82 = 0; var82 < state.noStates; var82 += 1)
					inferSample84(var82);
			}
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				if(!state.fixedFlag$sample104)
					inferSample104(sample);
				if(!state.fixedFlag$sample123) {
					for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
						inferSample123(sample, timeStep$var113);
				}
			}
		} else {
			for(int sample = (state.noSamples - 1); sample >= 0; sample -= 1) {
				if(!state.fixedFlag$sample123) {
					for(int timeStep$var113 = (state.length$metric[sample] - 1); timeStep$var113 >= 1; timeStep$var113 -= 1)
						inferSample123(sample, timeStep$var113);
				}
				if(!state.fixedFlag$sample104)
					inferSample104(sample);
			}
			if(!state.fixedFlag$sample84) {
				for(int var82 = (state.noStates - 1); var82 >= 0; var82 -= 1)
					inferSample84(var82);
			}
			if(!state.fixedFlag$sample68) {
				for(int var66 = (state.noStates - 1); var66 >= 0; var66 -= 1)
					inferSample68(var66);
			}
			if(!state.fixedFlag$sample52) {
				for(int var50 = (state.noStates - 1); var50 >= 0; var50 -= 1)
					inferSample52(var50);
			}
			if(!state.fixedFlag$sample32) {
				for(int var31 = (state.noStates - 1); var31 >= 0; var31 -= 1)
					inferSample32(var31);
			}
			if(!state.fixedFlag$sample19)
				inferSample19();
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample19)
			drawValueSample19();
		for(int var31 = 0; var31 < state.noStates; var31 += 1) {
			if(!state.constrainedFlag$sample32[var31])
				drawValueSample32(var31);
		}
		for(int var50 = 0; var50 < state.noStates; var50 += 1) {
			if(!state.constrainedFlag$sample52[var50])
				drawValueSample52(var50);
		}
		for(int var66 = 0; var66 < state.noStates; var66 += 1) {
			if(!state.constrainedFlag$sample68[var66])
				drawValueSample68(var66);
		}
		for(int var82 = 0; var82 < state.noStates; var82 += 1) {
			if(!state.constrainedFlag$sample84[var82])
				drawValueSample84(var82);
		}
		for(int sample = 0; sample < state.noSamples; sample += 1) {
			if(!state.constrainedFlag$sample104[sample])
				drawValueSample104(sample);
			for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1) {
				if(!state.constrainedFlag$sample123[sample][(timeStep$var113 - 1)])
					drawValueSample123(sample, timeStep$var113);
			}
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample19)
			state.logProbability$initialStateDistribution = Double.NaN;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample32)
			state.logProbability$var32 = Double.NaN;
		state.logProbability$metric_mean = 0.0;
		if(!state.fixedProbFlag$sample52)
			state.logProbability$var51 = Double.NaN;
		state.logProbability$metric_var = 0.0;
		if(!state.fixedProbFlag$sample68)
			state.logProbability$var67 = Double.NaN;
		state.logProbability$metric_valid_bias = 0.0;
		if(!state.fixedProbFlag$sample84)
			state.logProbability$var83 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample104) {
			for(int sample = 0; sample < state.noSamples; sample += 1)
				state.logProbability$sample104[sample] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample123) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var113 = 1; timeStep$var113 < state.length$metric[sample]; timeStep$var113 += 1)
					state.logProbability$sample123[sample][(timeStep$var113 - 1)] = Double.NaN;
			}
		}
		state.logProbability$metric_valid_1d = 0.0;
		state.logProbability$metric_valid_g = 0.0;
		if(!state.fixedProbFlag$sample145) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					state.logProbability$sample145[sample][timeStep$var136] = Double.NaN;
			}
		}
		state.logProbability$var151 = 0.0;
		state.logProbability$metric_g = 0.0;
		if(!state.fixedProbFlag$sample157) {
			for(int sample = 0; sample < state.noSamples; sample += 1) {
				for(int timeStep$var136 = 0; timeStep$var136 < state.length$metric[sample]; timeStep$var136 += 1)
					state.logProbability$sample157[sample][timeStep$var136] = Double.NaN;
			}
		}
	}

	@Override
	public final void initializeModel() {
		state.noSamples = state.length$metric.length;
		for(int var15 = 0; var15 < state.noStates; var15 += 1)
			state.v[var15] = 0.1;
		for(int index$constrainedFlag$sample32$1 = 0; index$constrainedFlag$sample32$1 < state.constrainedFlag$sample32.length; index$constrainedFlag$sample32$1 += 1)
			state.constrainedFlag$sample32[index$constrainedFlag$sample32$1] = true;
		for(int index$constrainedFlag$sample123$1 = 0; index$constrainedFlag$sample123$1 < state.constrainedFlag$sample123.length; index$constrainedFlag$sample123$1 += 1) {
			boolean[] cv$constrainedFlag$sample123$1 = state.constrainedFlag$sample123[index$constrainedFlag$sample123$1];
			for(int index$constrainedFlag$sample123$2 = 0; index$constrainedFlag$sample123$2 < cv$constrainedFlag$sample123$1.length; index$constrainedFlag$sample123$2 += 1)
				cv$constrainedFlag$sample123$1[index$constrainedFlag$sample123$2] = true;
		}
		for(int index$constrainedFlag$sample104$1 = 0; index$constrainedFlag$sample104$1 < state.constrainedFlag$sample104.length; index$constrainedFlag$sample104$1 += 1)
			state.constrainedFlag$sample104[index$constrainedFlag$sample104$1] = true;
		for(int index$constrainedFlag$sample84$1 = 0; index$constrainedFlag$sample84$1 < state.constrainedFlag$sample84.length; index$constrainedFlag$sample84$1 += 1)
			state.constrainedFlag$sample84[index$constrainedFlag$sample84$1] = true;
		for(int index$constrainedFlag$sample68$1 = 0; index$constrainedFlag$sample68$1 < state.constrainedFlag$sample68.length; index$constrainedFlag$sample68$1 += 1)
			state.constrainedFlag$sample68[index$constrainedFlag$sample68$1] = true;
		for(int index$constrainedFlag$sample52$1 = 0; index$constrainedFlag$sample52$1 < state.constrainedFlag$sample52.length; index$constrainedFlag$sample52$1 += 1)
			state.constrainedFlag$sample52[index$constrainedFlag$sample52$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample19)
			logProbabilityValue$sample19();
		if(state.fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(state.fixedFlag$sample52)
			logProbabilityValue$sample52();
		if(state.fixedFlag$sample68)
			logProbabilityValue$sample68();
		if(state.fixedFlag$sample84)
			logProbabilityValue$sample84();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityDistribution$sample104();
		logProbabilityDistribution$sample123();
		logProbabilityDistribution$sample145();
		logProbabilityDistribution$sample157();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
		logProbabilityValue$sample32();
		logProbabilityValue$sample52();
		logProbabilityValue$sample68();
		logProbabilityValue$sample84();
		logProbabilityValue$sample104();
		logProbabilityValue$sample123();
		logProbabilityValue$sample145();
		logProbabilityValue$sample157();
	}

	@Override
	public final void propogateObservedValues() {
		state.fixedFlag$sample157 = false;
		int cv$length1 = state.metric_valid_g.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1) {
			boolean[] cv$source2 = state.metric_valid[cv$index1];
			boolean[] cv$target2 = state.metric_valid_g[cv$index1];
			int cv$length2 = cv$target2.length;
			for(int cv$index2 = 0; cv$index2 < cv$length2; cv$index2 += 1)
				cv$target2[cv$index2] = cv$source2[cv$index2];
		}
		for(int sample = (state.noSamples - 1); sample >= 0; sample -= 1) {
			for(int timeStep$var136 = (state.length$metric[sample] - 1); timeStep$var136 >= 0; timeStep$var136 -= 1) {
				state.metric_g[sample][timeStep$var136] = state.metric[sample][timeStep$var136];
				if(state.metric_valid_g[sample][timeStep$var136])
					state.var151[sample][timeStep$var136] = state.metric_g[sample][timeStep$var136];
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
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " *\n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "model HMMMetrics2(\n"
		     + "               double[][] metric,\n"
		     + "               boolean[][] metric_valid, \n"
		     + "               int noStates) {\n"
		     + "    \n"
		     + "    int noSamples = metric.length;\n"
		     + "\n"
		     + "    // Construct arrays describing the probability of a move from 1 state to another.\n"
		     + "    double[] v = new double[noStates] <~ 0.1;\n"
		     + "    double[] initialStateDistribution = dirichlet(v).sample();\n"
		     + "    double[][] m = dirichlet(v).sample(noStates);\n"
		     + "\n"
		     + "    //Allocate space for states\n"
		     + "    int[][] st = new int[noSamples][];\n"
		     + "\n"
		     + "    //Allocate space for generated metrics \n"
		     + "    double[][] metric_g = new double[noSamples][];\n"
		     + "    boolean[][] metric_valid_g = new boolean[noSamples][];\n"
		     + "    \n"
		     + "    //Calculate priors for the metric\n"
		     + "    double[] metric_mean = uniform(0.0, 100.0).sample(noStates);\n"
		     + "    double[] metric_var = inverseGamma(1.0, 1.0).sample(noStates);\n"
		     + "    double[] metric_valid_bias = beta(1.0, 1.0).sample(noStates);\n"
		     + "    \n"
		     + "    // Compute the values of each metric value\n"
		     + "    for(int sample = 0; sample < noSamples; sample++) {\n"
		     + "        //Calculate all the state transitions\n"
		     + "        int streamLength = metric[sample].length;\n"
		     + "        \n"
		     + "        // Allocate space for the state.\n"
		     + "        st[sample] = new int[streamLength];\n"
		     + "        \n"
		     + "        // Set the initial state by sampling from a categorical with learnt weightings.\n"
		     + "        st[sample][0] = categorical(initialStateDistribution).sampleDistribution();\n"
		     + "        \n"
		     + "        // Calculate the remaining weightings\n"
		     + "        for(int timeStep = 1; timeStep < streamLength; timeStep++)\n"
		     + "            st[sample][timeStep] = categorical(m[st[sample][timeStep-1]]).sampleDistribution();\n"
		     + "        \n"
		     + "        //Calculate metric values\n"
		     + "        double[] metric_1d = new double[streamLength];\n"
		     + "        metric_g[sample] = metric_1d;\n"
		     + "\n"
		     + "        boolean[] metric_valid_1d = new boolean[streamLength];\n"
		     + "        metric_valid_g[sample] = metric_valid_1d;\n"
		     + "\n"
		     + "        //Generate values.\n"
		     + "        for(int timeStep = 0; timeStep < streamLength; timeStep++){\n"
		     + "            int currentState = st[sample][timeStep];\n"
		     + "            \n"
		     + "            metric_valid_1d[timeStep] = bernoulli(metric_valid_bias[currentState]).sample();\n"
		     + "            if(metric_valid_1d[timeStep])\n"
		     + "                metric_1d[timeStep] = gaussian(metric_mean[currentState], metric_var[currentState]).sample();\n"
		     + "            // Observing here as a cast is required and doing it inside the for loops removes the need duplicate them later.\n"
		     + "            metric_1d[timeStep].observe(metric[sample][timeStep]);\n"
		     + "        }\n"
		     + "    }\n"
		     + "\n"
		     + "    //Tie the values to the measured values.\n"
		     + "    metric_valid_g.observe(metric_valid);\n"
		     + "}";
	}
}