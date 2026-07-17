package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestPart5b$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestPart5b.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestPart5b$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var28$countGlobal;
		double[] cv$var52$stateProbabilityGlobal;
		double[] cv$var75$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var28$countGlobal = new double[2];
			cv$var52$stateProbabilityGlobal = new double[2];
			cv$var75$stateProbabilityGlobal = new double[2];
		}
	}


	public HMMTestPart5b$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample28(int var27) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[var27]);
	}

	private final void drawValueSample45(int var43) {
		state.bias[var43] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample53() {
		state.st[0] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2) * 2);
	}

	private final void drawValueSample76(int i$var67) {
		state.st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var67 - 4)]], 2) * 2);
	}

	private final void inferSample28(int var27) {
		state.constrainedFlag$sample28[var27] = false;
		scratch.cv$var28$countGlobal[0] = 0.0;
		scratch.cv$var28$countGlobal[1] = 0.0;
		if(((var27 == 0) && (state.fixedFlag$sample53 || state.constrainedFlag$sample53))) {
			state.constrainedFlag$sample28[0] = true;
			scratch.cv$var28$countGlobal[(state.st[0] / 2)] = (scratch.cv$var28$countGlobal[(state.st[0] / 2)] + 1.0);
		}
		for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1) {
			if(((var27 == state.st[(i$var67 - 4)]) && (state.fixedFlag$sample76 || state.constrainedFlag$sample76[(i$var67 - 4)]))) {
				state.constrainedFlag$sample28[var27] = true;
				scratch.cv$var28$countGlobal[(state.st[(i$var67 - 3)] / 2)] = (scratch.cv$var28$countGlobal[(state.st[(i$var67 - 3)] / 2)] + 1.0);
			}
		}
		if(state.constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var28$countGlobal, state.m[var27], 2);
	}

	private final void inferSample45(int var43) {
		state.constrainedFlag$sample45[var43] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 5; j < (state.samples + 5); j += 1) {
			if((var43 == state.st[(j - 5)])) {
				state.constrainedFlag$sample45[var43] = true;
				cv$count = (cv$count + 1);
				if(state.flips[(j - 5)])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample45[var43])
			state.bias[var43] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample53() {
		state.constrainedFlag$sample53 = false;
		{
			state.st[0] = 0;
			double[] var50 = state.m[0];
			double cv$accumulatedProbabilities = (((0.0 <= var50[0]) && (var50[0] <= 1.0))?Math.log(var50[0]):Double.NEGATIVE_INFINITY);
			if(((1 < state.samples) && (state.fixedFlag$sample76 || state.constrainedFlag$sample76[0]))) {
				state.constrainedFlag$sample53 = true;
				double[] var73 = state.m[0];
				cv$accumulatedProbabilities = ((((((0.0 <= (state.st[1] / 2)) && ((state.st[1] / 2) < 2)) && (0.0 <= var73[(state.st[1] / 2)])) && (var73[(state.st[1] / 2)] <= 1.0))?Math.log(var73[(state.st[1] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < state.samples)) {
				state.constrainedFlag$sample53 = true;
				double var96 = state.bias[0];
				cv$accumulatedProbabilities = ((((0.0 <= var96) && (var96 <= 1.0))?Math.log((state.flips[0]?var96:(1.0 - var96))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var52$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.st[0] = 2;
		double[] var50 = state.m[0];
		double cv$accumulatedProbabilities = (((0.0 <= var50[1]) && (var50[1] <= 1.0))?Math.log(var50[1]):Double.NEGATIVE_INFINITY);
		if(((1 < state.samples) && (state.fixedFlag$sample76 || state.constrainedFlag$sample76[0]))) {
			state.constrainedFlag$sample53 = true;
			double[] var73 = state.m[2];
			cv$accumulatedProbabilities = ((((((0.0 <= (state.st[1] / 2)) && ((state.st[1] / 2) < 2)) && (0.0 <= var73[(state.st[1] / 2)])) && (var73[(state.st[1] / 2)] <= 1.0))?Math.log(var73[(state.st[1] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if((0 < state.samples)) {
			state.constrainedFlag$sample53 = true;
			double var96 = state.bias[2];
			cv$accumulatedProbabilities = ((((0.0 <= var96) && (var96 <= 1.0))?Math.log((state.flips[0]?var96:(1.0 - var96))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		scratch.cv$var52$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample53) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var52$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var52$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var52$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var52$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var52$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var52$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var52$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var52$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var52$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[0] = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var52$stateProbabilityGlobal, 2) * 2);
		}
	}

	private final void inferSample76(int i$var67) {
		state.constrainedFlag$sample76[(i$var67 - 4)] = false;
		{
			state.st[(i$var67 - 3)] = 0;
			double[] var73 = state.m[state.st[(i$var67 - 4)]];
			double cv$accumulatedProbabilities = (((0.0 <= var73[0]) && (var73[0] <= 1.0))?Math.log(var73[0]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i$var67 + 1);
			if(((index$i$2_2 < (state.samples + 3)) && (state.fixedFlag$sample76 || state.constrainedFlag$sample76[(index$i$2_2 - 4)]))) {
				double[] sc$var73$1 = state.m[0];
				cv$accumulatedProbabilities = ((((((0.0 <= (state.st[(index$i$2_2 - 3)] / 2)) && ((state.st[(index$i$2_2 - 3)] / 2) < 2)) && (0.0 <= sc$var73$1[(state.st[(index$i$2_2 - 3)] / 2)])) && (sc$var73$1[(state.st[(index$i$2_2 - 3)] / 2)] <= 1.0))?Math.log(sc$var73$1[(state.st[(index$i$2_2 - 3)] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			state.constrainedFlag$sample76[(i$var67 - 4)] = true;
			double var96 = state.bias[0];
			cv$accumulatedProbabilities = ((((0.0 <= var96) && (var96 <= 1.0))?Math.log((state.flips[(i$var67 - 3)]?var96:(1.0 - var96))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			scratch.cv$var75$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.st[(i$var67 - 3)] = 2;
		double[] var73 = state.m[state.st[(i$var67 - 4)]];
		double cv$accumulatedProbabilities = (((0.0 <= var73[1]) && (var73[1] <= 1.0))?Math.log(var73[1]):Double.NEGATIVE_INFINITY);
		int index$i$2_2 = (i$var67 + 1);
		if(((index$i$2_2 < (state.samples + 3)) && (state.fixedFlag$sample76 || state.constrainedFlag$sample76[(index$i$2_2 - 4)]))) {
			double[] sc$var73$1 = state.m[2];
			cv$accumulatedProbabilities = ((((((0.0 <= (state.st[(index$i$2_2 - 3)] / 2)) && ((state.st[(index$i$2_2 - 3)] / 2) < 2)) && (0.0 <= sc$var73$1[(state.st[(index$i$2_2 - 3)] / 2)])) && (sc$var73$1[(state.st[(index$i$2_2 - 3)] / 2)] <= 1.0))?Math.log(sc$var73$1[(state.st[(index$i$2_2 - 3)] / 2)]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		state.constrainedFlag$sample76[(i$var67 - 4)] = true;
		double var96 = state.bias[2];
		cv$accumulatedProbabilities = ((((0.0 <= var96) && (var96 <= 1.0))?Math.log((state.flips[(i$var67 - 3)]?var96:(1.0 - var96))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		scratch.cv$var75$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample76[(i$var67 - 4)]) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var75$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var75$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var75$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var75$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var75$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var75$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var75$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var75$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var75$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var75$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var75$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var75$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var75$stateProbabilityGlobal, 2) * 2);
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

	private final void logProbabilityValue$sample53() {
		if(!state.fixedProbFlag$sample53) {
			int cv$sampleValue = (state.st[0] / 2);
			double[] var50 = state.m[0];
			double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var50[cv$sampleValue])) && (var50[cv$sampleValue] <= 1.0))?Math.log(var50[cv$sampleValue]):Double.NEGATIVE_INFINITY);
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

	private final void logProbabilityValue$sample76() {
		if(!state.fixedProbFlag$sample76) {
			double cv$accumulator = 0.0;
			for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1) {
				int cv$sampleValue = (state.st[(i$var67 - 3)] / 2);
				double[] var73 = state.m[state.st[(i$var67 - 4)]];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var73[cv$sampleValue])) && (var73[cv$sampleValue] <= 1.0))?Math.log(var73[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample76[(i$var67 - 4)] = cv$distributionAccumulator;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample76 = ((state.fixedFlag$sample76 && state.fixedFlag$sample28) && state.fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample76[(i$var67 - 4)]);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample76)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample99() {
		if(!state.fixedProbFlag$sample99) {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (state.samples + 5); j += 1) {
				double var96 = state.bias[state.st[(j - 5)]];
				double cv$distributionAccumulator = (((0.0 <= var96) && (var96 <= 1.0))?Math.log((state.flips[(j - 5)]?var96:(1.0 - var96))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample99[(j - 5)] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample99 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample76);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 5; j < (state.samples + 5); j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample99[(j - 5)]);
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2) * 2);
		if(!state.fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1)
				state.st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var67 - 4)]], 2) * 2);
		}
		for(int j = 5; j < (state.samples + 5); j += 1)
			state.flips[(j - 5)] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[(j - 5)]]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2) * 2);
		if(!state.fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1)
				state.st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var67 - 4)]], 2) * 2);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2) * 2);
		if(!state.fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1)
				state.st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var67 - 4)]], 2) * 2);
		}
		for(int j = 5; j < (state.samples + 5); j += 1)
			state.flips[(j - 5)] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[(j - 5)]]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2) * 2);
		if(!state.fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1)
				state.st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var67 - 4)]], 2) * 2);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample28) {
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2) * 2);
		if(!state.fixedFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1)
				state.st[(i$var67 - 3)] = (DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var67 - 4)]], 2) * 2);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample28) {
				inferSample28(0);
				inferSample28(1);
			}
			if(!state.fixedFlag$sample45) {
				inferSample45(0);
				inferSample45(1);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			if(!state.fixedFlag$sample76) {
				for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1)
					inferSample76(i$var67);
			}
		} else {
			if(!state.fixedFlag$sample76) {
				for(int i$var67 = (state.samples + 2); i$var67 >= 4; i$var67 -= 1)
					inferSample76(i$var67);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			if(!state.fixedFlag$sample45) {
				inferSample45(1);
				inferSample45(0);
			}
			if(!state.fixedFlag$sample28) {
				inferSample28(1);
				inferSample28(0);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample28[0])
			drawValueSample28(0);
		if(!state.constrainedFlag$sample28[1])
			drawValueSample28(1);
		if(!state.constrainedFlag$sample45[0])
			drawValueSample45(0);
		if(!state.constrainedFlag$sample45[1])
			drawValueSample45(1);
		if(!state.constrainedFlag$sample53)
			drawValueSample53();
		for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1) {
			if(!state.constrainedFlag$sample76[(i$var67 - 4)])
				drawValueSample76(i$var67);
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
		if(!state.fixedProbFlag$sample76) {
			for(int i$var67 = 4; i$var67 < (state.samples + 3); i$var67 += 1)
				state.logProbability$sample76[(i$var67 - 4)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample99) {
			for(int j = 5; j < (state.samples + 5); j += 1)
				state.logProbability$sample99[(j - 5)] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.v[0] = 0.1;
		state.v[1] = 0.1;
		state.samples = state.length$flipsMeasured;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample76$1 = 0; index$constrainedFlag$sample76$1 < state.constrainedFlag$sample76.length; index$constrainedFlag$sample76$1 += 1)
			state.constrainedFlag$sample76[index$constrainedFlag$sample76$1] = true;
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
		if(state.fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(state.fixedFlag$sample76)
			logProbabilityValue$sample76();
		logProbabilityValue$sample99();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample76();
		logProbabilityValue$sample99();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample76();
		logProbabilityValue$sample99();
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
		     + "model HMMTestPart5b(boolean[] flipsMeasured) {\n"
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
		     + "        int[] st = new int[samples];\n"
		     + "\n"
		     + "        st[0] = states * categorical(m[0]).sample();\n"
		     + "\n"
		     + "        for(int i:[4..samples + 3))\n"
		     + "            st[i-3] = states * categorical(m[st[i-4]]).sample();\n"
		     + "            \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "            \n"
		     + "        for(int j:[5..samples+5))\n"
		     + "            flips[j-5] = bernoulli(bias[st[j-5]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}