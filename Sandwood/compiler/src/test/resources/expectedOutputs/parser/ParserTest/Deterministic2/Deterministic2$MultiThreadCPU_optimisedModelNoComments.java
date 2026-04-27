package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Deterministic2$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Deterministic2.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Deterministic2$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$distributionAccumulator$var53;
		double[][] cv$var29$countGlobal;
		double[] cv$var54$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			int cv$threadCount = threadCount();
			cv$var29$countGlobal = new double[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				cv$var29$countGlobal[cv$index] = new double[5];
			cv$distributionAccumulator$var53 = new double[5];
			cv$var54$stateProbabilityGlobal = new double[5];
		}
	}


	public Deterministic2$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample29(int var28, int threadID$cv$var28, Rng RNG$) {
		DistributionSampling.sampleDirichlet(RNG$, state.v, 5, state.m[var28]);
	}

	private final void drawValueSample55(int i$var46) {
		state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		int index$i$2_1 = (i$var46 + 1);
		if((index$i$2_1 < state.n))
			state.b[index$i$2_1] = state.a[(index$i$2_1 - 1)];
	}

	private final void inferSample29(int var28, int threadID$cv$var28, Rng RNG$) {
		state.constrainedFlag$sample29[var28] = false;
		double[] cv$countLocal = scratch.cv$var29$countGlobal[threadID$cv$var28];
		for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
			cv$countLocal[cv$loopIndex] = 0.0;
		if(state.fixedFlag$sample55) {
			if(((1 < state.n) && (var28 == 0))) {
				state.constrainedFlag$sample29[0] = true;
				cv$countLocal[state.a[1]] = (cv$countLocal[state.a[1]] + 1.0);
			}
			for(int i$var46 = 2; i$var46 < state.n; i$var46 += 1) {
				if((var28 == state.a[(i$var46 - 1)])) {
					state.constrainedFlag$sample29[var28] = true;
					cv$countLocal[state.a[i$var46]] = (cv$countLocal[state.a[i$var46]] + 1.0);
				}
			}
		} else {
			if(((1 < state.n) && (var28 == 0))) {
				for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
					cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + state.distribution$sample55[0][cv$loopIndex]);
			}
			if((var28 < 5)) {
				for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
					int index$i$27 = (i$var46 - 1);
					if((1 <= index$i$27)) {
						double cv$distributionProbability = state.distribution$sample55[(index$i$27 - 1)][var28];
						for(int cv$loopIndex = 0; cv$loopIndex < 5; cv$loopIndex += 1)
							cv$countLocal[cv$loopIndex] = (cv$countLocal[cv$loopIndex] + (state.distribution$sample55[(i$var46 - 1)][cv$loopIndex] * cv$distributionProbability));
					}
				}
			}
		}
		if(state.constrainedFlag$sample29[var28])
			Conjugates.sampleConjugateDirichletCategorical(RNG$, state.v, cv$countLocal, state.m[var28], 5);
	}

	private final void inferSample55(int i$var46) {
		state.constrainedFlag$sample55[(i$var46 - 1)] = false;
		int cv$numStates = 0;
		if((1 == i$var46))
			cv$numStates = 5;
		int index$i$5 = (i$var46 - 1);
		if(((1 <= index$i$5) && !(index$i$5 == i$var46)))
			cv$numStates = 5;
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			double cv$stateProbabilityValue = Double.NEGATIVE_INFINITY;
			double cv$reachedDistributionSourceRV = 0.0;
			double cv$accumulatedDistributionProbabilities = 0.0;
			if((1 == i$var46)) {
				cv$reachedDistributionSourceRV = 1.0;
				double[] var52 = state.m[0];
				state.constrainedFlag$sample55[0] = true;
				double var72 = (double)(1 / cv$valuePos);
				cv$stateProbabilityValue = ((((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[0]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY) + (((0.0 <= var52[cv$valuePos]) && (var52[cv$valuePos] <= 1.0))?Math.log(var52[cv$valuePos]):Double.NEGATIVE_INFINITY));
			}
			int index$i$15 = (i$var46 - 1);
			if(((1 <= index$i$15) && !(index$i$15 == i$var46))) {
				for(int index$sample55$16 = 0; index$sample55$16 < 5; index$sample55$16 += 1) {
					double cv$probabilitySample55Value17 = state.distribution$sample55[(index$i$15 - 1)][index$sample55$16];
					cv$reachedDistributionSourceRV = (cv$reachedDistributionSourceRV + cv$probabilitySample55Value17);
					double[] var52 = state.m[index$sample55$16];
					state.constrainedFlag$sample55[(i$var46 - 1)] = true;
					double var72 = (double)(1 / index$sample55$16);
					double cv$accumulatedProbabilities = (((((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[(i$var46 - 1)]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY) + Math.log(cv$probabilitySample55Value17)) + (((0.0 <= var52[cv$valuePos]) && (var52[cv$valuePos] <= 1.0))?Math.log(var52[cv$valuePos]):Double.NEGATIVE_INFINITY));
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
			int index$i$34_2 = (i$var46 + 1);
			if((index$i$34_2 < state.n)) {
				for(int cv$i = 0; cv$i < 5; cv$i += 1)
					scratch.cv$distributionAccumulator$var53[cv$i] = 0.0;
				double scopeVariable$reachedSourceProbability = 0.0;
				if((1 == i$var46))
					scopeVariable$reachedSourceProbability = 1.0;
				int index$i$41 = (i$var46 - 1);
				if((((1 <= index$i$41) && !(index$i$41 == i$var46)) && !(index$i$41 == index$i$34_2))) {
					for(int index$sample55$42 = 0; index$sample55$42 < 5; index$sample55$42 += 1)
						scopeVariable$reachedSourceProbability = (scopeVariable$reachedSourceProbability + state.distribution$sample55[(index$i$41 - 1)][index$sample55$42]);
				}
				DistributionSampling.addProbabilityDistributionCategorical(scratch.cv$distributionAccumulator$var53, scopeVariable$reachedSourceProbability, state.m[cv$valuePos], 5);
				double[] cv$sampleDistribution = state.distribution$sample55[(index$i$34_2 - 1)];
				double cv$overlap = 0.0;
				for(int cv$i = 0; cv$i < 5; cv$i += 1) {
					double cv$normalisedDistValue = (scratch.cv$distributionAccumulator$var53[cv$i] / scopeVariable$reachedSourceProbability);
					double cv$sampleDistValue = cv$sampleDistribution[cv$i];
					if((cv$sampleDistValue < cv$normalisedDistValue))
						cv$overlap = (cv$overlap + cv$sampleDistValue);
					else
						cv$overlap = (cv$overlap + cv$normalisedDistValue);
				}
				cv$accumulatedDistributionProbabilities = Math.log((((cv$overlap * scopeVariable$reachedSourceProbability) + 1.0) - Math.min(scopeVariable$reachedSourceProbability, 1.0)));
			}
			scratch.cv$var54$stateProbabilityGlobal[cv$valuePos] = ((cv$stateProbabilityValue + cv$accumulatedDistributionProbabilities) - Math.log(cv$reachedDistributionSourceRV));
		}
		if(state.constrainedFlag$sample55[(i$var46 - 1)]) {
			double[] cv$localProbability = state.distribution$sample55[(i$var46 - 1)];
			double cv$logSum;
			double cv$lseMax = scratch.cv$var54$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var54$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var54$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					cv$localProbability[cv$indexName] = Math.exp((scratch.cv$var54$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var54$stateProbabilityGlobal.length; cv$indexName += 1)
				cv$localProbability[cv$indexName] = Double.NEGATIVE_INFINITY;
		}
	}

	private final void logProbabilityDistribution$sample55() {
		if(!state.fixedProbFlag$sample55) {
			if(state.fixedFlag$sample55) {
				double cv$accumulator = 0.0;
				for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
					double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
					double cv$probabilityReached = 0.0;
					int cv$sampleValue = state.a[i$var46];
					if((1 == i$var46)) {
						double[] var52 = state.m[0];
						cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
						cv$probabilityReached = 1.0;
					}
					if((2 <= i$var46)) {
						int traceTempVariable$var51$6_3 = state.a[(i$var46 - 1)];
						if(((0 <= traceTempVariable$var51$6_3) && (traceTempVariable$var51$6_3 < 5))) {
							double[] var52 = state.m[traceTempVariable$var51$6_3];
							double cv$weightedProbability = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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
					state.logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
					if((i$var46 < (state.n - 1)))
						state.logProbability$b = (state.logProbability$b + cv$distributionAccumulator);
				}
				state.logProbability$a = (state.logProbability$a + cv$accumulator);
				state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
				state.fixedProbFlag$sample55 = state.fixedFlag$sample29;
			}
		} else {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double cv$sampleValue = state.logProbability$sample55[(i$var46 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				if((state.fixedFlag$sample55 && (i$var46 < (state.n - 1))))
					state.logProbability$b = (state.logProbability$b + cv$sampleValue);
			}
			if(state.fixedFlag$sample55)
				state.logProbability$a = (state.logProbability$a + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityDistribution$sample75() {
		if(!state.fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.n; j += 1) {
				double cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				double cv$probabilityReached = 0.0;
				boolean cv$sampleValue = state.flips[j];
				if(state.fixedFlag$sample55) {
					if((j < (state.n - 1))) {
						double var72 = (double)(1 / state.a[(j + 1)]);
						cv$distributionAccumulator = (((0.0 <= var72) && (var72 <= 1.0))?Math.log((cv$sampleValue?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY);
						cv$probabilityReached = 1.0;
					}
				} else {
					int i$var46 = (j + 1);
					if((i$var46 < state.n)) {
						for(int index$sample55$5 = 0; index$sample55$5 < 5; index$sample55$5 += 1) {
							double cv$probabilitySample55Value6 = state.distribution$sample55[(i$var46 - 1)][index$sample55$5];
							double var72 = (double)(1 / index$sample55$5);
							double cv$weightedProbability = (Math.log(cv$probabilitySample55Value6) + (((0.0 <= var72) && (var72 <= 1.0))?Math.log((cv$sampleValue?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY));
							if((cv$weightedProbability < cv$distributionAccumulator))
								cv$distributionAccumulator = (Math.log((Math.exp((cv$weightedProbability - cv$distributionAccumulator)) + 1)) + cv$distributionAccumulator);
							else {
								if((cv$distributionAccumulator == Double.NEGATIVE_INFINITY))
									cv$distributionAccumulator = cv$weightedProbability;
								else
									cv$distributionAccumulator = (Math.log((Math.exp((cv$distributionAccumulator - cv$weightedProbability)) + 1)) + cv$weightedProbability);
							}
							cv$probabilityReached = (cv$probabilityReached + cv$probabilitySample55Value6);
						}
					}
				}
				if((cv$probabilityReached == 0.0))
					cv$distributionAccumulator = Double.NEGATIVE_INFINITY;
				else
					cv$distributionAccumulator = (cv$distributionAccumulator - Math.log(cv$probabilityReached));
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample75[j] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample75 = state.fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.n; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample75[j]);
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample29() {
		if(!state.fixedProbFlag$sample29) {
			double cv$sampleAccumulator = 0.0;
			for(int var28 = 0; var28 < 5; var28 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var28], state.v, 5));
			state.logProbability$var29 = cv$sampleAccumulator;
			state.logProbability$m = (state.logProbability$m + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample29)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample29 = state.fixedFlag$sample29;
		} else {
			state.logProbability$m = (state.logProbability$m + state.logProbability$var29);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var29);
			if(state.fixedFlag$sample29)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var29);
		}
	}

	private final void logProbabilityValue$sample55() {
		if(!state.fixedProbFlag$sample55) {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				int cv$sampleValue = state.a[i$var46];
				double[] var52 = state.m[state.b[i$var46]];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 5)) && (0.0 <= var52[cv$sampleValue])) && (var52[cv$sampleValue] <= 1.0))?Math.log(var52[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample55[(i$var46 - 1)] = cv$distributionAccumulator;
				if((i$var46 < (state.n - 1)))
					state.logProbability$b = (state.logProbability$b + cv$distributionAccumulator);
			}
			state.logProbability$a = (state.logProbability$a + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample55 = (state.fixedFlag$sample55 && state.fixedFlag$sample29);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double cv$sampleValue = state.logProbability$sample55[(i$var46 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				if((i$var46 < (state.n - 1)))
					state.logProbability$b = (state.logProbability$b + cv$sampleValue);
			}
			state.logProbability$a = (state.logProbability$a + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample55)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample75() {
		if(!state.fixedProbFlag$sample75) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.n; j += 1) {
				double var72 = (double)(1 / state.a[(j + 1)]);
				double cv$distributionAccumulator = (((0.0 <= var72) && (var72 <= 1.0))?Math.log((state.flips[j]?var72:(1.0 - var72))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample75[j] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample75 = state.fixedFlag$sample55;
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.n; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample75[j]);
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				state.b[i$var46] = state.a[(i$var46 - 1)];
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
			}
		}
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / state.a[(j + 1)]));
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				double[] cv$distribution$sample55 = state.distribution$sample55[(i$var46 - 1)];
				for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
					cv$distribution$sample55[index$var53] = 0.0;
				if((1 == i$var46)) {
					double[] var52 = state.m[0];
					for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
						cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (((0.0 <= var52[index$var53]) && (var52[index$var53] <= 1.0))?var52[index$var53]:0.0));
				}
				int index$i$4 = (i$var46 - 1);
				if((1 <= index$i$4)) {
					for(int index$sample55$5 = 0; index$sample55$5 < 5; index$sample55$5 += 1) {
						double cv$probabilitySample55Value6 = state.distribution$sample55[(index$i$4 - 1)][index$sample55$5];
						double[] var52 = state.m[index$sample55$5];
						for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
							cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] + (cv$probabilitySample55Value6 * (((0.0 <= var52[index$var53]) && (var52[index$var53] <= 1.0))?var52[index$var53]:0.0)));
					}
				}
				double cv$var53$sum = 0.0;
				for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
					cv$var53$sum = (cv$var53$sum + cv$distribution$sample55[index$var53]);
				for(int index$var53 = 0; index$var53 < 5; index$var53 += 1)
					cv$distribution$sample55[index$var53] = (cv$distribution$sample55[index$var53] / cv$var53$sum);
			}
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
		parallelFor(state.RNG$, 0, state.n, 1,
			(int forStart$j, int forEnd$j, int threadID$j, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int j = forStart$j; j < forEnd$j; j += 1)
						state.flips[j] = DistributionSampling.sampleBernoulli(RNG$1, (1 / state.a[(j + 1)]));
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		if(!state.fixedFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
				state.b[i$var46] = state.a[(i$var46 - 1)];
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
			}
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample29)
			parallelFor(state.RNG$, 0, 5, 1,
				(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
							DistributionSampling.sampleDirichlet(RNG$1, state.v, 5, state.m[var28]);
				}
			);

		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			state.b[i$var46] = state.a[(i$var46 - 1)];
			if(!state.fixedFlag$sample55)
				state.a[i$var46] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.b[i$var46]], 5);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample29)
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								inferSample29(var28, threadID$var28, RNG$1);
					}
				);

			if(!state.fixedFlag$sample55) {
				for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
					inferSample55(i$var46);
			}
		} else {
			if(!state.fixedFlag$sample55) {
				for(int i$var46 = (state.n - 1); i$var46 >= 1; i$var46 -= 1)
					inferSample55(i$var46);
			}
			if(!state.fixedFlag$sample29)
				parallelFor(state.RNG$, 0, 5, 1,
					(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1)
								inferSample29(var28, threadID$var28, RNG$1);
					}
				);

		}
		state.system$gibbsForward = !state.system$gibbsForward;
		parallelFor(state.RNG$, 0, 5, 1,
			(int forStart$var28, int forEnd$var28, int threadID$var28, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var28 = forStart$var28; var28 < forEnd$var28; var28 += 1) {
						if(!state.constrainedFlag$sample29[var28])
							drawValueSample29(var28, threadID$var28, RNG$1);
					}
			}
		);
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1) {
			if(!state.constrainedFlag$sample55[(i$var46 - 1)])
				drawValueSample55(i$var46);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample29)
			state.logProbability$var29 = Double.NaN;
		state.logProbability$a = 0.0;
		state.logProbability$b = 0.0;
		if(!state.fixedProbFlag$sample55) {
			for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
				state.logProbability$sample55[(i$var46 - 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample75) {
			for(int j = 0; j < state.n; j += 1)
				state.logProbability$sample75[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int i$var14 = 0; i$var14 < 5; i$var14 += 1)
			state.v[i$var14] = 0.1;
		state.a[0] = 0;
		for(int index$constrainedFlag$sample29$1 = 0; index$constrainedFlag$sample29$1 < state.constrainedFlag$sample29.length; index$constrainedFlag$sample29$1 += 1)
			state.constrainedFlag$sample29[index$constrainedFlag$sample29$1] = true;
		for(int index$constrainedFlag$sample55$1 = 0; index$constrainedFlag$sample55$1 < state.constrainedFlag$sample55.length; index$constrainedFlag$sample55$1 += 1)
			state.constrainedFlag$sample55[index$constrainedFlag$sample55$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample29)
			logProbabilityValue$sample29();
		logProbabilityValue$sample75();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityDistribution$sample55();
		logProbabilityDistribution$sample75();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample29();
		logProbabilityValue$sample55();
		logProbabilityValue$sample75();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.flipsMeasured[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		for(int i$var46 = 1; i$var46 < state.n; i$var46 += 1)
			state.b[i$var46] = state.a[(i$var46 - 1)];
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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model Deterministic2(int n, boolean[] flipsMeasured) {\n"
		     + "    int states = 5;\n"
		     + "\n"
		     + "    double[] v = new double[states];\n"
		     + "    for(int i:[0..states))\n"
		     + "        v[i] = 0.1;\n"
		     + "    \n"
		     + "    double[][] m = dirichlet(v).sample(states);\n"
		     + "\n"
		     + "    int[] a = new int[n];\n"
		     + "    int[] b = new int[n];\n"
		     + "    a[0] = 0;\n"
		     + "    for(int i=1; i<n; i++) {\n"
		     + "        b[i] = a[i-1];\n"
		     + "        a[i] = categorical(m[b[i]]).sampleDistribution();\n"
		     + "    }\n"
		     + "    \n"
		     + "    boolean[] flips = new boolean[n];\n"
		     + "            \n"
		     + "    for(int j:[0..n))\n"
		     + "        flips[j] = bernoulli(1/a[j+1]).sample();\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}";
	}
}