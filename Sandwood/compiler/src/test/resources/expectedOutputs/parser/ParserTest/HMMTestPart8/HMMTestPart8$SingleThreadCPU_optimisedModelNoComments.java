package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart8$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart8.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart8$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$distributionAccumulator$var69;
		double[] cv$var28$countGlobal;
		double[] cv$var52$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var28$countGlobal = new double[5];
			cv$distributionAccumulator$var69 = new double[5];
			cv$var52$stateProbabilityGlobal = new double[5];
			cv$var70$stateProbabilityGlobal = new double[5];
		}
	}


	public HMMTestPart8$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample28(int var27) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, 5, state.m[var27]);
	}

	private final void drawValueSample45(int var43) {
		state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample53() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
	}

	private final void drawValueSample71(int i$var64) {
		state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
	}

	private final void inferSample28(int var27) {
		state.constrainedFlag$sample28[var27] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			scratch.cv$var28$countGlobal[cv$loopIndex] = 0.0;
		if(((var27 == 0) && (state.fixedFlag$sample53 || state.constrainedFlag$sample53))) {
			state.constrainedFlag$sample28[0] = true;
			scratch.cv$var28$countGlobal[state.st[0]] = (scratch.cv$var28$countGlobal[state.st[0]] + 1.0);
		}
		if(state.fixedFlag$sample71) {
			if(((var27 == state.st[0]) && (1 < state.samples))) {
				state.constrainedFlag$sample28[var27] = true;
				scratch.cv$var28$countGlobal[state.st[1]] = (scratch.cv$var28$countGlobal[state.st[1]] + 1.0);
			}
			for(int i$var64 = 2; i$var64 < state.samples; i$var64 += 1) {
				if((var27 == state.st[(i$var64 - 1)])) {
					state.constrainedFlag$sample28[var27] = true;
					scratch.cv$var28$countGlobal[state.st[i$var64]] = (scratch.cv$var28$countGlobal[state.st[i$var64]] + 1.0);
				}
			}
		} else {
			if(((var27 == state.st[0]) && (1 < state.samples))) {
				for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
					scratch.cv$var28$countGlobal[cv$loopIndex] = (scratch.cv$var28$countGlobal[cv$loopIndex] + state.distribution$sample71[0][cv$loopIndex]);
			}
			if((var27 < 5)) {
				for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
					int index$i$30 = (i$var64 - 1);
					if((1 <= index$i$30)) {
						double cv$distributionProbability = state.distribution$sample71[(index$i$30 - 1)][var27];
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							scratch.cv$var28$countGlobal[cv$loopIndex] = (scratch.cv$var28$countGlobal[cv$loopIndex] + (state.distribution$sample71[(i$var64 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(state.constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var28$countGlobal, state.m[var27], 5);
	}

	private final void inferSample45(int var43) {
		state.constrainedFlag$sample45[var43] = false;
		double cv$sum = 0.0;
		double cv$count = 0.0;
		if(((var43 == state.st[0]) && (0 < state.samples))) {
			state.constrainedFlag$sample45[var43] = true;
			cv$count = 1.0;
			if(state.flips[0])
				cv$sum = 1.0;
		}
		for(int j = 1; j < state.samples; j += 1) {
			if(state.fixedFlag$sample71) {
				if((var43 == state.st[j])) {
					state.constrainedFlag$sample45[var43] = true;
					cv$count = (cv$count + 1.0);
					if(state.flips[j])
						cv$sum = (cv$sum + 1.0);
				}
			} else {
				if((var43 < 5)) {
					double cv$probabilitySample71Value8 = state.distribution$sample71[(j - 1)][var43];
					state.constrainedFlag$sample45[var43] = true;
					cv$count = (cv$count + cv$probabilitySample71Value8);
					if(state.flips[j])
						cv$sum = (cv$sum + cv$probabilitySample71Value8);
				}
			}
		}
		if(state.constrainedFlag$sample45[var43])
			state.bias[var43] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample53() {
		state.constrainedFlag$sample53 = false;
		for(int cv$valuePos = 0; cv$valuePos < 5; cv$valuePos += 1) {
			double cv$accumulatedDistributionProbabilities = 0.0;
			state.st[0] = cv$valuePos;
			double[] var50 = state.m[0];
			double cv$accumulatedProbabilities = (((0.0 <= var50[cv$valuePos]) && (var50[cv$valuePos] <= 1.0))?Math.log(var50[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if((state.fixedFlag$sample71 && (1 < state.samples))) {
				state.constrainedFlag$sample53 = true;
				double[] var68 = state.m[cv$valuePos];
				cv$accumulatedProbabilities = ((((((0.0 <= state.st[1]) && (state.st[1] < 5)) && (0.0 <= var68[state.st[1]])) && (var68[state.st[1]] <= 1.0))?Math.log(var68[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < state.samples)) {
				state.constrainedFlag$sample53 = true;
				double var84 = state.bias[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[0]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((!state.fixedFlag$sample71 && (1 < state.samples))) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					scratch.cv$distributionAccumulator$var69[cv$i] = 0.0;
				DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var69, 1.0, state.m[cv$valuePos], 5);
				double[] cv$sampleDistribution = state.distribution$sample71[0];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = scratch.cv$distributionAccumulator$var69[cv$i];
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log(cv$overlap);
			}
			scratch.cv$var52$stateProbabilityGlobal[cv$valuePos] = (cv$accumulatedProbabilities + cv$accumulatedDistributionProbabilities);
		}
		if(state.constrainedFlag$sample53) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var52$stateProbabilityGlobal[0];
			{
				double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[1];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[2];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			{
				double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[3];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[4];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < 5; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var52$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
					scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = 0.2;
			} else {
				for(int cv$indexName = 0; cv$indexName < 5; cv$indexName += 1)
					scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var52$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = 5; cv$indexName < scratch.cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var52$stateProbabilityGlobal, 5);
		}
	}

	private final void inferSample71(int i$var64) {
		state.constrainedFlag$sample71[(i$var64 - 1)] = false;
		int cv$numStates = 0;
		if((1 == i$var64)) {
			int var27 = state.st[0];
			if(((0 <= var27) && (var27 < 5)))
				cv$numStates = 5;
		}
		int index$i$5 = (i$var64 - 1);
		if(((1 <= index$i$5) && !(index$i$5 == i$var64)))
			cv$numStates = 5;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var64)) {
				int var27 = state.st[0];
				if(((0 <= var27) && (var27 < 5))) {
					cv$reachedDistributionSourceRV = 1.0;
					double[] var68 = state.m[state.st[0]];
					state.constrainedFlag$sample71[0] = true;
					double var84 = state.bias[cv$valuePos];
					cv$stateProbabilityValue = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[1]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + (((0.0 <= var68[cv$valuePos]) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY));
				}
			}
			int index$i$15 = (i$var64 - 1);
			if(((1 <= index$i$15) && !(index$i$15 == i$var64))) {
				for(int index$sample71$16 = 0; index$sample71$16 < 5; index$sample71$16 += 1) {
					double cv$probabilitySample71Value17 = state.distribution$sample71[(index$i$15 - 1)][index$sample71$16];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample71Value17);
					double[] var68 = state.m[index$sample71$16];
					state.constrainedFlag$sample71[(i$var64 - 1)] = true;
					double var84 = state.bias[index$sample71$16];
					double cv$accumulatedProbabilities = (((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[i$var64]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample71Value17)) + (((0.0 <= var68[cv$valuePos]) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY));
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
			int index$i$37_2 = (i$var64 + 1);
			if((index$i$37_2 < state.samples)) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					scratch.cv$distributionAccumulator$var69[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == i$var64)) {
					int index$var27$42_1 = state.st[0];
					if(((0 <= index$var27$42_1) && (index$var27$42_1 < 5)))
						scopeVariable$reachedSourceProbability = 1.0;
				}
				int index$i$44 = (i$var64 - 1);
				if((((1 <= index$i$44) && !(index$i$44 == i$var64)) && !(index$i$44 == index$i$37_2))) {
					for(int index$sample71$45 = 0; index$sample71$45 < 5; index$sample71$45 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample71[(index$i$44 - 1)][index$sample71$45]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var69, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], 5);
				double[] cv$sampleDistribution = state.distribution$sample71[(index$i$37_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var69[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			scratch.cv$var70$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample71[(i$var64 - 1)]) {
			double[] cv$localProbability = state.distribution$sample71[(i$var64 - 1)];
			double cv$logSum;
			double cv$lseMax = scratch.cv$var70$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var70$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var70$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void logProbabilityDistribution$sample71() {
		if(!state.fixedProbFlag$sample71) {
			if(state.fixedFlag$sample71) {
				double cv$accumulator = 0.0;
				for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = state.st[i$var64];
					if((1 == i$var64)) {
						int var27 = state.st[0];
						if(((0 <= var27) && (var27 < 5))) {
							double[] var68 = state.m[state.st[0]];
							cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
							cv$probabilityReached = 1.0;
						}
					}
					if((2 <= i$var64)) {
						int var27 = state.st[(i$var64 - 1)];
						if(((0 <= var27) && (var27 < 5))) {
							double[] var68 = state.m[state.st[(i$var64 - 1)]];
							double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					state.logProbability$sample71[(i$var64 - 1)] = cv$distributionAccumulator;
				}
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample71 = (state.fixedFlag$sample28 && state.fixedFlag$sample53);
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample71[(i$var64 - 1)]);
			if(state.fixedFlag$sample71)
				state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample87() {
		if(!state.fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.samples; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = state.flips[j];
				if((0 == j)) {
					int var43 = state.st[0];
					if(((0 <= var43) && (var43 < 5))) {
						double var84 = state.bias[state.st[0]];
						cv$distributionAccumulator = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
						cv$probabilityReached = 1.0;
					}
				}
				if((1 <= j)) {
					if(state.fixedFlag$sample71) {
						int var43 = state.st[j];
						if(((0 <= var43) && (var43 < 5))) {
							double var84 = state.bias[state.st[j]];
							double cv$weightedProbability = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
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
						for(int index$sample71$6 = 0; index$sample71$6 < 5; index$sample71$6 += 1) {
							double cv$probabilitySample71Value7 = state.distribution$sample71[(j - 1)][index$sample71$6];
							double var84 = state.bias[index$sample71$6];
							double cv$weightedProbability = (Math.log(cv$probabilitySample71Value7) + (((0.0 <= var84) && (var84 <= 1.0))?Math.log((cv$sampleValue?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample71Value7);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample87[j] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.samples; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample87[j]);
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < 5; var27 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var27], state.v, 5));
			state.logProbability$var28 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			state.logProbability$m = (state.logProbability$m + state.logProbability$var28);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var28);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var28);
		}
	}

	private final void logProbabilityValue$sample45() {
		if(!state.fixedProbFlag$sample45) {
			double cv$sampleAccumulator = 0.0;
			for(int var43 = 0; var43 < 5; var43 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.bias[var43], 1.0, 1.0));
			state.logProbability$var44 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample45 = state.fixedFlag$sample45;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var44);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var44);
			if(state.fixedFlag$sample45)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var44);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!state.fixedProbFlag$sample53) {
			int cv$sampleValue = state.st[0];
			double[] var50 = state.m[0];
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			state.logProbability$var52 = cv$distributionAccumulator;
			state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample53)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample53 = (state.fixedFlag$sample53 && state.fixedFlag$sample28);
		} else {
			state.logProbability$st = (state.logProbability$st + state.logProbability$var52);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var52);
			if(state.fixedFlag$sample53)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var52);
		}
	}

	private final void logProbabilityValue$sample71() {
		if(!state.fixedProbFlag$sample71) {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				int cv$sampleValue = state.st[i$var64];
				double[] var68 = state.m[state.st[(i$var64 - 1)]];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample71[(i$var64 - 1)] = cv$distributionAccumulator;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample71 = ((state.fixedFlag$sample71 && state.fixedFlag$sample28) && state.fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample71[(i$var64 - 1)]);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!state.fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.samples; j += 1) {
				double var84 = state.bias[state.st[j]];
				double cv$distributionAccumulator = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample87[j] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.samples; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample87[j]);
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, 5, state.m[var27]);
		}
		if(!state.fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
		}
		for(int j = 0; j < state.samples; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[j]]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, 5, state.m[var27]);
		}
		if(!state.fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
				double[] cv$distribution$sample71 = state.distribution$sample71[(i$var64 - 1)];
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					cv$distribution$sample71[index$var69] = 0.0;
				if((1 == i$var64)) {
					int var27 = state.st[0];
					if(((0 <= var27) && (var27 < 5))) {
						double[] var68 = state.m[state.st[0]];
						for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
							cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (((0.0 <= var68[index$var69]) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0));
					}
				}
				int index$i$4 = (i$var64 - 1);
				if((1 <= index$i$4)) {
					for(int index$sample71$5 = 0; index$sample71$5 < 5; index$sample71$5 += 1) {
						double cv$probabilitySample71Value6 = state.distribution$sample71[(index$i$4 - 1)][index$sample71$5];
						double[] var68 = state.m[index$sample71$5];
						for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
							cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] + (cv$probabilitySample71Value6 * (((0.0 <= var68[index$var69]) && (var68[index$var69] <= 1.0))?var68[index$var69]:0.0)));
					}
				}
				double cv$var69$sum = 0.0;
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					cv$var69$sum = (cv$var69$sum + cv$distribution$sample71[index$var69]);
				for(int index$var69 = 0; index$var69 < 5; index$var69 += 1)
					cv$distribution$sample71[index$var69] = (cv$distribution$sample71[index$var69] / cv$var69$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, 5, state.m[var27]);
		}
		if(!state.fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
		}
		for(int j = 0; j < state.samples; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[j]]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, 5, state.m[var27]);
		}
		if(!state.fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < 5; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, 5, state.m[var27]);
		}
		if(!state.fixedFlag$sample45) {
			for(int var43 = 0; var43 < 5; var43 += 1)
				state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 5);
		if(!state.fixedFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.st[i$var64] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var64 - 1)]], 5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample28) {
				for(int var27 = 0; var27 < 5; var27 += 1)
					inferSample28(var27);
			}
			if(!state.fixedFlag$sample45) {
				for(int var43 = 0; var43 < 5; var43 += 1)
					inferSample45(var43);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			if(!state.fixedFlag$sample71) {
				for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
					inferSample71(i$var64);
			}
		} else {
			if(!state.fixedFlag$sample71) {
				for(int i$var64 = (state.samples - 1); i$var64 >= 1; i$var64 -= 1)
					inferSample71(i$var64);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			if(!state.fixedFlag$sample45) {
				for(int var43 = 4; var43 >= 0; var43 -= 1)
					inferSample45(var43);
			}
			if(!state.fixedFlag$sample28) {
				for(int var27 = 4; var27 >= 0; var27 -= 1)
					inferSample28(var27);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var27 = 0; var27 < 5; var27 += 1) {
			if(!state.constrainedFlag$sample28[var27])
				drawValueSample28(var27);
		}
		for(int var43 = 0; var43 < 5; var43 += 1) {
			if(!state.constrainedFlag$sample45[var43])
				drawValueSample45(var43);
		}
		if(!state.constrainedFlag$sample53)
			drawValueSample53();
		for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1) {
			if(!state.constrainedFlag$sample71[(i$var64 - 1)])
				drawValueSample71(i$var64);
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
		if(!state.fixedProbFlag$sample53)
			state.logProbability$var52 = Double.NaN;
		if(!state.fixedProbFlag$sample71) {
			for(int i$var64 = 1; i$var64 < state.samples; i$var64 += 1)
				state.logProbability$sample71[(i$var64 - 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample87) {
			for(int j = 0; j < state.samples; j += 1)
				state.logProbability$sample87[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int i$var13 = 0; i$var13 < 5; i$var13 += 1)
			state.v[i$var13] = 0.1;
		state.samples = state.length$flipsMeasured;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample71$1 = 0; index$constrainedFlag$sample71$1 < state.constrainedFlag$sample71.length; index$constrainedFlag$sample71$1 += 1)
			state.constrainedFlag$sample71[index$constrainedFlag$sample71$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(state.fixedFlag$sample53)
			logProbabilityValue$sample53();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityDistribution$sample71();
		logProbabilityDistribution$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
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
		     + "model HMMTestPart8(boolean[] flipsMeasured) {\n"
		     + "        int states = 5;\n"
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
		     + "        st[0] = categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i-1]]).sampleDistribution();\n"
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