package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart3d$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart3d.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart3d$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[][] cv$var28$countGlobal;
		double[] cv$var53$stateProbabilityGlobal;
		double[] cv$var78$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			int cv$threadCount = threadCount();
			cv$var28$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var28$countGlobal[cv$index] = new double[2];
			cv$var53$stateProbabilityGlobal = new double[2];
			cv$var78$stateProbabilityGlobal = new double[2];
		}
	}


	public HMMTestPart3d$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, 2, state.m[var27]);
	}

	private final void drawValueSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		state.bias[var43] = DistributionSampling.sampleBeta(RNG$, 1.0, 1.0);
	}

	private final void drawValueSample54() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71)))
				state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
	}

	private final void drawValueSample79(int i$var71) {
		state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], 2);
		for(int index$i$1_1 = 1; index$i$1_1 < state.samples; index$i$1_1 += 1) {
			if((i$var71 == (state.indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)))
				state.st2[(state.indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)] = (state.samples - state.st[(state.indirection[(index$i$1_1 - 1)][index$i$1_1] / index$i$1_1)]);
		}
	}

	private final void inferSample28(int var27, int threadID$cv$var27, Rng RNG$) {
		state.constrainedFlag$sample28[var27] = false;
		double[] cv$countLocal = scratch.cv$var28$countGlobal[threadID$cv$var27];
		cv$countLocal[0] = 0.0;
		cv$countLocal[1] = 0.0;
		if(((var27 == 0) && (state.fixedFlag$sample54 || state.constrainedFlag$sample54))) {
			state.constrainedFlag$sample28[0] = true;
			cv$countLocal[state.st[0]] = (cv$countLocal[state.st[0]] + 1.0);
		}
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(((var27 == (state.samples - state.st2[(i$var71 - 1)])) && (state.fixedFlag$sample79 || state.constrainedFlag$sample79[(i$var71 - 1)]))) {
				state.constrainedFlag$sample28[var27] = true;
				cv$countLocal[state.st[i$var71]] = (cv$countLocal[state.st[i$var71]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var27], 2);
	}

	private final void inferSample45(int var43, int threadID$cv$var43, Rng RNG$) {
		state.constrainedFlag$sample45[var43] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < state.samples; j += 1) {
			if((var43 == (state.samples - state.st2[j]))) {
				state.constrainedFlag$sample45[var43] = true;
				cv$count = (cv$count + 1);
				if(state.flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample45[var43])
			state.bias[var43] = Conjugates.sampleConjugateBetaBinomial(RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample54() {
		state.constrainedFlag$sample54 = false;
		{
			state.st[0] = 0;
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				if((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71)))
					state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
			}
			double[] var51 = state.m[0];
			double cv$accumulatedProbabilities = (((0.0 <= var51[0]) && (var51[0] <= 1.0))?Math.log(var51[0]):Double.NEGATIVE_INFINITY);
			if(((1 < state.samples) && (state.fixedFlag$sample79 || state.constrainedFlag$sample79[0]))) {
				state.constrainedFlag$sample54 = true;
				double[] var76 = state.m[0];
				cv$accumulatedProbabilities = ((((((0.0 <= state.st[1]) && (state.st[1] < 2)) && (0.0 <= var76[state.st[1]])) && (var76[state.st[1]] <= 1.0))?Math.log(var76[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				if((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71))) {
					int index$i$5_4 = ((state.indirection[(i$var71 - 1)][i$var71] / i$var71) + 1);
					if((((1 <= index$i$5_4) && (index$i$5_4 < state.samples)) && (state.fixedFlag$sample79 || state.constrainedFlag$sample79[(index$i$5_4 - 1)]))) {
						state.constrainedFlag$sample54 = true;
						double[] var76 = state.m[0];
						cv$accumulatedProbabilities = ((((((0.0 <= state.st[index$i$5_4]) && (state.st[index$i$5_4] < 2)) && (0.0 <= var76[state.st[index$i$5_4]])) && (var76[state.st[index$i$5_4]] <= 1.0))?Math.log(var76[state.st[index$i$5_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			if((0 < state.samples)) {
				state.constrainedFlag$sample54 = true;
				double var116 = state.bias[0];
				cv$accumulatedProbabilities = ((((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[0]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				if((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71))) {
					int j = (state.indirection[(i$var71 - 1)][i$var71] / i$var71);
					if(((0 <= j) && (j < state.samples))) {
						state.constrainedFlag$sample54 = true;
						double var116 = state.bias[0];
						cv$accumulatedProbabilities = ((((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			scratch.cv$var53$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.st[0] = 1;
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71)))
				state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
		double[] var51 = state.m[0];
		double cv$accumulatedProbabilities = (((0.0 <= var51[1]) && (var51[1] <= 1.0))?Math.log(var51[1]):Double.NEGATIVE_INFINITY);
		if(((1 < state.samples) && (state.fixedFlag$sample79 || state.constrainedFlag$sample79[0]))) {
			state.constrainedFlag$sample54 = true;
			double[] var76 = state.m[1];
			cv$accumulatedProbabilities = ((((((0.0 <= state.st[1]) && (state.st[1] < 2)) && (0.0 <= var76[state.st[1]])) && (var76[state.st[1]] <= 1.0))?Math.log(var76[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71))) {
				int index$i$5_4 = ((state.indirection[(i$var71 - 1)][i$var71] / i$var71) + 1);
				if((((1 <= index$i$5_4) && (index$i$5_4 < state.samples)) && (state.fixedFlag$sample79 || state.constrainedFlag$sample79[(index$i$5_4 - 1)]))) {
					state.constrainedFlag$sample54 = true;
					double[] var76 = state.m[1];
					cv$accumulatedProbabilities = ((((((0.0 <= state.st[index$i$5_4]) && (state.st[index$i$5_4] < 2)) && (0.0 <= var76[state.st[index$i$5_4]])) && (var76[state.st[index$i$5_4]] <= 1.0))?Math.log(var76[state.st[index$i$5_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		if((0 < state.samples)) {
			state.constrainedFlag$sample54 = true;
			double var116 = state.bias[1];
			cv$accumulatedProbabilities = ((((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[0]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71))) {
				int j = (state.indirection[(i$var71 - 1)][i$var71] / i$var71);
				if(((0 <= j) && (j < state.samples))) {
					state.constrainedFlag$sample54 = true;
					double var116 = state.bias[1];
					cv$accumulatedProbabilities = ((((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		scratch.cv$var53$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample54) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var53$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var53$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var53$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var53$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var53$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var53$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var53$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var53$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var53$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var53$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var53$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var53$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var53$stateProbabilityGlobal, 2);
			state.st2[0] = (state.samples - state.st[0]);
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				if((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71)))
					state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
			}
		}
	}

	private final void inferSample79(int i$var71) {
		state.constrainedFlag$sample79[(i$var71 - 1)] = false;
		{
			state.st[i$var71] = 0;
			for(int index$i$2_1 = 1; index$i$2_1 < state.samples; index$i$2_1 += 1) {
				if((i$var71 == (state.indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)))
					state.st2[(state.indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)] = (state.samples - state.st[(state.indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)]);
			}
			double[] var76 = state.m[(state.samples - state.st2[(i$var71 - 1)])];
			double cv$accumulatedProbabilities = (((0.0 <= var76[0]) && (var76[0] <= 1.0))?Math.log(var76[0]):Double.NEGATIVE_INFINITY);
			for(int index$i$3_2 = 1; index$i$3_2 < state.samples; index$i$3_2 += 1) {
				if((i$var71 == (state.indirection[(index$i$3_2 - 1)][index$i$3_2] / index$i$3_2))) {
					int index$i$3_4 = ((state.indirection[(index$i$3_2 - 1)][index$i$3_2] / index$i$3_2) + 1);
					if((((1 <= index$i$3_4) && (index$i$3_4 < state.samples)) && (state.fixedFlag$sample79 || state.constrainedFlag$sample79[(index$i$3_4 - 1)]))) {
						state.constrainedFlag$sample79[(i$var71 - 1)] = true;
						double[] sc$var76$1 = state.m[0];
						cv$accumulatedProbabilities = ((((((0.0 <= state.st[index$i$3_4]) && (state.st[index$i$3_4] < 2)) && (0.0 <= sc$var76$1[state.st[index$i$3_4]])) && (sc$var76$1[state.st[index$i$3_4]] <= 1.0))?Math.log(sc$var76$1[state.st[index$i$3_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			for(int index$i$6_2 = 1; index$i$6_2 < state.samples; index$i$6_2 += 1) {
				if((i$var71 == (state.indirection[(index$i$6_2 - 1)][index$i$6_2] / index$i$6_2))) {
					int j = (state.indirection[(index$i$6_2 - 1)][index$i$6_2] / index$i$6_2);
					if(((0 <= j) && (j < state.samples))) {
						state.constrainedFlag$sample79[(i$var71 - 1)] = true;
						double var116 = state.bias[0];
						cv$accumulatedProbabilities = ((((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
					}
				}
			}
			scratch.cv$var78$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.st[i$var71] = 1;
		for(int index$i$2_1 = 1; index$i$2_1 < state.samples; index$i$2_1 += 1) {
			if((i$var71 == (state.indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)))
				state.st2[(state.indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)] = (state.samples - state.st[(state.indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)]);
		}
		double[] var76 = state.m[(state.samples - state.st2[(i$var71 - 1)])];
		double cv$accumulatedProbabilities = (((0.0 <= var76[1]) && (var76[1] <= 1.0))?Math.log(var76[1]):Double.NEGATIVE_INFINITY);
		for(int index$i$3_2 = 1; index$i$3_2 < state.samples; index$i$3_2 += 1) {
			if((i$var71 == (state.indirection[(index$i$3_2 - 1)][index$i$3_2] / index$i$3_2))) {
				int index$i$3_4 = ((state.indirection[(index$i$3_2 - 1)][index$i$3_2] / index$i$3_2) + 1);
				if((((1 <= index$i$3_4) && (index$i$3_4 < state.samples)) && (state.fixedFlag$sample79 || state.constrainedFlag$sample79[(index$i$3_4 - 1)]))) {
					state.constrainedFlag$sample79[(i$var71 - 1)] = true;
					double[] sc$var76$1 = state.m[1];
					cv$accumulatedProbabilities = ((((((0.0 <= state.st[index$i$3_4]) && (state.st[index$i$3_4] < 2)) && (0.0 <= sc$var76$1[state.st[index$i$3_4]])) && (sc$var76$1[state.st[index$i$3_4]] <= 1.0))?Math.log(sc$var76$1[state.st[index$i$3_4]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		for(int index$i$6_2 = 1; index$i$6_2 < state.samples; index$i$6_2 += 1) {
			if((i$var71 == (state.indirection[(index$i$6_2 - 1)][index$i$6_2] / index$i$6_2))) {
				int j = (state.indirection[(index$i$6_2 - 1)][index$i$6_2] / index$i$6_2);
				if(((0 <= j) && (j < state.samples))) {
					state.constrainedFlag$sample79[(i$var71 - 1)] = true;
					double var116 = state.bias[1];
					cv$accumulatedProbabilities = ((((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
		}
		scratch.cv$var78$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample79[(i$var71 - 1)]) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var78$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var78$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var78$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var78$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var78$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var78$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var78$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var78$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var78$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var78$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var78$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var78$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var78$stateProbabilityGlobal, 2);
			for(int index$i$10_1 = 1; index$i$10_1 < state.samples; index$i$10_1 += 1) {
				if((i$var71 == (state.indirection[(index$i$10_1 - 1)][index$i$10_1] / index$i$10_1)))
					state.st2[(state.indirection[(index$i$10_1 - 1)][index$i$10_1] / index$i$10_1)] = (state.samples - state.st[(state.indirection[(index$i$10_1 - 1)][index$i$10_1] / index$i$10_1)]);
			}
		}
	}

	private final void logProbabilityValue$sample119() {
		if(!state.fixedProbFlag$sample119) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.samples; j += 1) {
				double var116 = state.bias[(state.samples - state.st2[j])];
				double cv$distributionAccumulator = (((0.0 <= var116) && (var116 <= 1.0))?Math.log((state.flips[j]?var116:(1.0 - var116))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample119[j] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample119 = ((state.fixedFlag$sample45 && state.fixedFlag$sample54) && state.fixedFlag$sample79);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.samples; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample119[j]);
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityDirichlet(state.m[0], state.v, 2) + DistributionSampling.logProbabilityDirichlet(state.m[1], state.v, 2));
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
			double cv$sampleAccumulator = (DistributionSampling.logProbabilityBeta(state.bias[0], 1.0, 1.0) + DistributionSampling.logProbabilityBeta(state.bias[1], 1.0, 1.0));
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

	private final void logProbabilityValue$sample54() {
		if(!state.fixedProbFlag$sample54) {
			int cv$sampleValue = state.st[0];
			double[] var51 = state.m[0];
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var51[cv$sampleValue])) && (var51[cv$sampleValue] <= 1.0))?Math.log(var51[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			state.logProbability$var53 = cv$distributionAccumulator;
			state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
			boolean cv$guard$st2 = true;
			state.logProbability$st2 = (state.logProbability$st2 + cv$distributionAccumulator);
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				if(((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71)) && !cv$guard$st2)) {
					cv$guard$st2 = true;
					state.logProbability$st2 = (state.logProbability$st2 + cv$distributionAccumulator);
				}
			}
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample54)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample54 = (state.fixedFlag$sample54 && state.fixedFlag$sample28);
		} else {
			state.logProbability$st = (state.logProbability$st + state.logProbability$var53);
			boolean cv$guard$st2 = true;
			state.logProbability$st2 = (state.logProbability$st2 + state.logProbability$var53);
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				if(((0 == (state.indirection[(i$var71 - 1)][i$var71] / i$var71)) && !cv$guard$st2)) {
					cv$guard$st2 = true;
					state.logProbability$st2 = (state.logProbability$st2 + state.logProbability$var53);
				}
			}
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var53);
			if(state.fixedFlag$sample54)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var53);
		}
	}

	private final void logProbabilityValue$sample79() {
		if(!state.fixedProbFlag$sample79) {
			double cv$accumulator = 0.0;
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				int cv$sampleValue = state.st[i$var71];
				double[] var76 = state.m[(state.samples - state.st2[(i$var71 - 1)])];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var76[cv$sampleValue])) && (var76[cv$sampleValue] <= 1.0))?Math.log(var76[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample79[(i$var71 - 1)] = cv$distributionAccumulator;
				boolean cv$guard$st2 = false;
				for(int index$i$2_1 = 1; index$i$2_1 < state.samples; index$i$2_1 += 1) {
					if(((i$var71 == (state.indirection[(index$i$2_1 - 1)][index$i$2_1] / index$i$2_1)) && !cv$guard$st2)) {
						cv$guard$st2 = true;
						state.logProbability$st2 = (state.logProbability$st2 + cv$distributionAccumulator);
					}
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample79)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample79 = ((state.fixedFlag$sample79 && state.fixedFlag$sample28) && state.fixedFlag$sample54);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
				double cv$sampleValue = state.logProbability$sample79[(i$var71 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				boolean cv$guard$st2 = false;
				for(int index$i$3_1 = 1; index$i$3_1 < state.samples; index$i$3_1 += 1) {
					if(((i$var71 == (state.indirection[(index$i$3_1 - 1)][index$i$3_1] / index$i$3_1)) && !cv$guard$st2)) {
						cv$guard$st2 = true;
						state.logProbability$st2 = (state.logProbability$st2 + cv$sampleValue);
					}
				}
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample79)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample54) {
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
			state.st2[0] = (state.samples - state.st[0]);
		}
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], 2);
			if((!state.fixedFlag$sample54 || !state.fixedFlag$sample79))
				state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[(state.samples - state.st2[j])]);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample54)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], 2);
			state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample54)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], 2);
			state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
		parallelFor(state.RNG$, 0, state.samples, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, state.bias[(state.samples - state.st2[j])]);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample54) {
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
			state.st2[0] = (state.samples - state.st[0]);
		}
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], 2);
			if((!state.fixedFlag$sample54 || !state.fixedFlag$sample79))
				state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample28)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 2, state.m[var27]);
				}
			);

		if(!state.fixedFlag$sample45)
			parallelFor(state.RNG$, 0, 2, 1,
				(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
							state.bias[var43] = DistributionSampling.sampleBeta(RNG$1, 1.0, 1.0);
				}
			);

		if(!state.fixedFlag$sample54)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.fixedFlag$sample79)
				state.st[i$var71] = DistributionSampling.sampleCategorical(state.RNG$, state.m[(state.samples - state.st2[(i$var71 - 1)])], 2);
			state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample28)
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

			if(!state.fixedFlag$sample45)
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!state.fixedFlag$sample54)
				inferSample54();
			if(!state.fixedFlag$sample79) {
				for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1)
					inferSample79(i$var71);
			}
		} else {
			if(!state.fixedFlag$sample79) {
				for(int i$var71 = (state.samples - 1); i$var71 >= 1; i$var71 -= 1)
					inferSample79(i$var71);
			}
			if(!state.fixedFlag$sample54)
				inferSample54();
			if(!state.fixedFlag$sample45)
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1)
								inferSample45(var43, threadID$var43, RNG$1);
					}
				);

			if(!state.fixedFlag$sample28)
				parallelFor(state.RNG$, 0, 2, 1,
					(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1)
								inferSample28(var27, threadID$var27, RNG$1);
					}
				);

		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$var27, int forEnd$var27, int threadID$var27, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var27 = forStart$var27; var27 < forEnd$var27; var27 += 1) {
						if(!state.constrainedFlag$sample28[var27])
							drawValueSample28(var27, threadID$var27, RNG$1);
					}
			}
		);
		parallelFor(state.RNG$, 0, 2, 1,
			(int forStart$var43, int forEnd$var43, int threadID$var43, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var43 = forStart$var43; var43 < forEnd$var43; var43 += 1) {
						if(!state.constrainedFlag$sample45[var43])
							drawValueSample45(var43, threadID$var43, RNG$1);
					}
			}
		);
		if(!state.constrainedFlag$sample54)
			drawValueSample54();
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1) {
			if(!state.constrainedFlag$sample79[(i$var71 - 1)])
				drawValueSample79(i$var71);
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
		state.logProbability$st2 = 0.0;
		if(!state.fixedProbFlag$sample54)
			state.logProbability$var53 = Double.NaN;
		if(!state.fixedProbFlag$sample79) {
			for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1)
				state.logProbability$sample79[(i$var71 - 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample119) {
			for(int j = 0; j < state.samples; j += 1)
				state.logProbability$sample119[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.v[0] = 0.1;
		state.v[1] = 0.1;
		state.samples = state.length$flipsMeasured;
		for(int i$var71 = 1; i$var71 < state.length$flipsMeasured; i$var71 += 1) {
			for(int k = 0; k <= i$var71; k += 1)
				state.indirection[(i$var71 - 1)][k] = (k * i$var71);
		}
		for(int index$constrainedFlag$sample79$1 = 0; index$constrainedFlag$sample79$1 < state.constrainedFlag$sample79.length; index$constrainedFlag$sample79$1 += 1)
			state.constrainedFlag$sample79[index$constrainedFlag$sample79$1] = true;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample45)
			logProbabilityValue$sample45();
		if(state.fixedFlag$sample54)
			logProbabilityValue$sample54();
		if(state.fixedFlag$sample79)
			logProbabilityValue$sample79();
		logProbabilityValue$sample119();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample54();
		logProbabilityValue$sample79();
		logProbabilityValue$sample119();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample54();
		logProbabilityValue$sample79();
		logProbabilityValue$sample119();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		state.st2[0] = (state.samples - state.st[0]);
		for(int i$var71 = 1; i$var71 < state.samples; i$var71 += 1)
			state.st2[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)] = (state.samples - state.st[(state.indirection[(i$var71 - 1)][i$var71] / i$var71)]);
	}

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
		     + "model HMMTestPart3d(boolean[] flipsMeasured) {\n"
		     + "        int states = 2;\n"
		     + "\n"
		     + "        double[] v = new double[states];\n"
		     + "        for(int i:[0..states))\n"
		     + "            v[i] = 0.1;\n"
		     + "\n"
		     + "        double[][] m = dirichlet(v).sample(states);\n"
		     + "        double[] bias = beta(1.0, 1.0).sample(states);\n"
		     + "\n"
		     + "        int samples = flipsMeasured.length;\n"
		     + "        int[] st = new int[samples];\n"
		     + "        int[] st2 = new int[samples];\n"
		     + "\n"
		     + "        st[0] = categorical(m[0]).sample();\n"
		     + "        st2[0] = samples - st[0];\n"
		     + "\n"
		     + "        for(int i:[1..samples)) {\n"
		     + "            st[i] = categorical(m[samples - st2[i-1]]).sample();\n"
		     + "            \n"
		     + "            int[] indirection = new int[i+1];\n"
		     + "            for(int k:[0..i])\n"
		     + "                indirection[k] = k*i; \n"
		     + "                \n"
		     + "            st2[indirection[i]/i] = samples - st[indirection[i]/i];\n"
		     + "        }\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[samples - st2[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}