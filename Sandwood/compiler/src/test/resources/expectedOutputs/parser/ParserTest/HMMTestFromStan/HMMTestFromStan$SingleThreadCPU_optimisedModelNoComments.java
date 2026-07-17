package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMMTestFromStan$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMMTestFromStan.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMMTestFromStan$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var28$countGlobal;
		double[] cv$var52$stateProbabilityGlobal;
		double[] cv$var71$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var28$countGlobal = new double[2];
			cv$var52$stateProbabilityGlobal = new double[2];
			cv$var71$stateProbabilityGlobal = new double[2];
		}
	}


	public HMMTestFromStan$SingleThreadCPU(State state, ExecutionTarget target) {
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
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
	}

	private final void drawValueSample72(int i$var65) {
		state.st[i$var65] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var65 - 1)]], 2);
	}

	private final void inferSample28(int var27) {
		state.constrainedFlag$sample28[var27] = false;
		scratch.cv$var28$countGlobal[0] = 0.0;
		scratch.cv$var28$countGlobal[1] = 0.0;
		if(((var27 == 0) && (state.fixedFlag$sample53 || state.constrainedFlag$sample53))) {
			state.constrainedFlag$sample28[0] = true;
			scratch.cv$var28$countGlobal[state.st[0]] = (scratch.cv$var28$countGlobal[state.st[0]] + 1.0);
		}
		for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1) {
			if(((var27 == state.st[(i$var65 - 1)]) && (state.fixedFlag$sample72 || state.constrainedFlag$sample72[(i$var65 - 1)]))) {
				state.constrainedFlag$sample28[var27] = true;
				scratch.cv$var28$countGlobal[state.st[i$var65]] = (scratch.cv$var28$countGlobal[state.st[i$var65]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var28$countGlobal, state.m[var27], 2);
	}

	private final void inferSample45(int var43) {
		state.constrainedFlag$sample45[var43] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < state.samples; j += 1) {
			if((var43 == state.st[j])) {
				state.constrainedFlag$sample45[var43] = true;
				cv$count = (cv$count + 1);
				if(state.flips[j])
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
			if(((1 < state.samples) && (state.fixedFlag$sample72 || state.constrainedFlag$sample72[0]))) {
				state.constrainedFlag$sample53 = true;
				double[] var69 = state.m[0];
				cv$accumulatedProbabilities = ((((((0.0 <= state.st[1]) && (state.st[1] < 2)) && (0.0 <= var69[state.st[1]])) && (var69[state.st[1]] <= 1.0))?Math.log(var69[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < state.samples)) {
				state.constrainedFlag$sample53 = true;
				double var84 = state.bias[0];
				cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[0]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var52$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.st[0] = 1;
		double[] var50 = state.m[0];
		double cv$accumulatedProbabilities = (((0.0 <= var50[1]) && (var50[1] <= 1.0))?Math.log(var50[1]):Double.NEGATIVE_INFINITY);
		if(((1 < state.samples) && (state.fixedFlag$sample72 || state.constrainedFlag$sample72[0]))) {
			state.constrainedFlag$sample53 = true;
			double[] var69 = state.m[1];
			cv$accumulatedProbabilities = ((((((0.0 <= state.st[1]) && (state.st[1] < 2)) && (0.0 <= var69[state.st[1]])) && (var69[state.st[1]] <= 1.0))?Math.log(var69[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		if((0 < state.samples)) {
			state.constrainedFlag$sample53 = true;
			double var84 = state.bias[1];
			cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[0]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
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
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var52$stateProbabilityGlobal, 2);
		}
	}

	private final void inferSample72(int i$var65) {
		state.constrainedFlag$sample72[(i$var65 - 1)] = false;
		{
			state.st[i$var65] = 0;
			double[] var69 = state.m[state.st[(i$var65 - 1)]];
			double cv$accumulatedProbabilities = (((0.0 <= var69[0]) && (var69[0] <= 1.0))?Math.log(var69[0]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i$var65 + 1);
			if(((index$i$2_2 < state.samples) && (state.fixedFlag$sample72 || state.constrainedFlag$sample72[(index$i$2_2 - 1)]))) {
				double[] sc$var69$1 = state.m[0];
				cv$accumulatedProbabilities = ((((((0.0 <= state.st[index$i$2_2]) && (state.st[index$i$2_2] < 2)) && (0.0 <= sc$var69$1[state.st[index$i$2_2]])) && (sc$var69$1[state.st[index$i$2_2]] <= 1.0))?Math.log(sc$var69$1[state.st[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			state.constrainedFlag$sample72[(i$var65 - 1)] = true;
			double var84 = state.bias[0];
			cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[i$var65]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			scratch.cv$var71$stateProbabilityGlobal[0] = cv$accumulatedProbabilities;
		}
		state.st[i$var65] = 1;
		double[] var69 = state.m[state.st[(i$var65 - 1)]];
		double cv$accumulatedProbabilities = (((0.0 <= var69[1]) && (var69[1] <= 1.0))?Math.log(var69[1]):Double.NEGATIVE_INFINITY);
		int index$i$2_2 = (i$var65 + 1);
		if(((index$i$2_2 < state.samples) && (state.fixedFlag$sample72 || state.constrainedFlag$sample72[(index$i$2_2 - 1)]))) {
			double[] sc$var69$1 = state.m[1];
			cv$accumulatedProbabilities = ((((((0.0 <= state.st[index$i$2_2]) && (state.st[index$i$2_2] < 2)) && (0.0 <= sc$var69$1[state.st[index$i$2_2]])) && (sc$var69$1[state.st[index$i$2_2]] <= 1.0))?Math.log(sc$var69$1[state.st[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		}
		state.constrainedFlag$sample72[(i$var65 - 1)] = true;
		double var84 = state.bias[1];
		cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[i$var65]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
		scratch.cv$var71$stateProbabilityGlobal[1] = cv$accumulatedProbabilities;
		if(state.constrainedFlag$sample72[(i$var65 - 1)]) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var71$stateProbabilityGlobal[0];
			double cv$lseElementValue = scratch.cv$var71$stateProbabilityGlobal[1];
			if((cv$lseMax < cv$lseElementValue))
				cv$lseMax = cv$lseElementValue;
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else
				cv$logSum = (Math.log((Math.exp((scratch.cv$var71$stateProbabilityGlobal[0] - cv$lseMax)) + Math.exp((scratch.cv$var71$stateProbabilityGlobal[1] - cv$lseMax)))) + cv$lseMax);
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				scratch.cv$var71$stateProbabilityGlobal[0] = 0.5;
				scratch.cv$var71$stateProbabilityGlobal[1] = 0.5;
			} else {
				scratch.cv$var71$stateProbabilityGlobal[0] = Math.exp((scratch.cv$var71$stateProbabilityGlobal[0] - cv$logSum));
				scratch.cv$var71$stateProbabilityGlobal[1] = Math.exp((scratch.cv$var71$stateProbabilityGlobal[1] - cv$logSum));
			}
			for(int cv$indexName = 2; cv$indexName < scratch.cv$var71$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var71$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[i$var65] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var71$stateProbabilityGlobal, 2);
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
			int cv$sampleValue = state.st[0];
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

	private final void logProbabilityValue$sample72() {
		if(!state.fixedProbFlag$sample72) {
			double cv$accumulator = 0.0;
			for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1) {
				int cv$sampleValue = state.st[i$var65];
				double[] var69 = state.m[state.st[(i$var65 - 1)]];
				double cv$distributionAccumulator = (((((0.0 <= cv$sampleValue) && (cv$sampleValue < 2)) && (0.0 <= var69[cv$sampleValue])) && (var69[cv$sampleValue] <= 1.0))?Math.log(var69[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample72[(i$var65 - 1)] = cv$distributionAccumulator;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample72)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample72 = ((state.fixedFlag$sample72 && state.fixedFlag$sample28) && state.fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample72[(i$var65 - 1)]);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample72)
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
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample45 && state.fixedFlag$sample53) && state.fixedFlag$sample72);
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
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[0]);
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, 2, state.m[1]);
		}
		if(!state.fixedFlag$sample45) {
			state.bias[0] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.bias[1] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample72) {
			for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1)
				state.st[i$var65] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var65 - 1)]], 2);
		}
		for(int j = 0; j < state.samples; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[j]]);
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
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample72) {
			for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1)
				state.st[i$var65] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var65 - 1)]], 2);
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
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample72) {
			for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1)
				state.st[i$var65] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var65 - 1)]], 2);
		}
		for(int j = 0; j < state.samples; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[j]]);
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
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample72) {
			for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1)
				state.st[i$var65] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var65 - 1)]], 2);
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
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.m[0], 2);
		if(!state.fixedFlag$sample72) {
			for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1)
				state.st[i$var65] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i$var65 - 1)]], 2);
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
			if(!state.fixedFlag$sample72) {
				for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1)
					inferSample72(i$var65);
			}
		} else {
			if(!state.fixedFlag$sample72) {
				for(int i$var65 = (state.samples - 1); i$var65 >= 1; i$var65 -= 1)
					inferSample72(i$var65);
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
		for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1) {
			if(!state.constrainedFlag$sample72[(i$var65 - 1)])
				drawValueSample72(i$var65);
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
		if(!state.fixedProbFlag$sample72) {
			for(int i$var65 = 1; i$var65 < state.samples; i$var65 += 1)
				state.logProbability$sample72[(i$var65 - 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample87) {
			for(int j = 0; j < state.samples; j += 1)
				state.logProbability$sample87[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.v[0] = 0.1;
		state.v[1] = 0.1;
		state.samples = state.length$flipsMeasured;
		for(int index$constrainedFlag$sample45$1 = 0; index$constrainedFlag$sample45$1 < state.constrainedFlag$sample45.length; index$constrainedFlag$sample45$1 += 1)
			state.constrainedFlag$sample45[index$constrainedFlag$sample45$1] = true;
		for(int index$constrainedFlag$sample28$1 = 0; index$constrainedFlag$sample28$1 < state.constrainedFlag$sample28.length; index$constrainedFlag$sample28$1 += 1)
			state.constrainedFlag$sample28[index$constrainedFlag$sample28$1] = true;
		for(int index$constrainedFlag$sample72$1 = 0; index$constrainedFlag$sample72$1 < state.constrainedFlag$sample72.length; index$constrainedFlag$sample72$1 += 1)
			state.constrainedFlag$sample72[index$constrainedFlag$sample72$1] = true;
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
		if(state.fixedFlag$sample72)
			logProbabilityValue$sample72();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample72();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample45();
		logProbabilityValue$sample53();
		logProbabilityValue$sample72();
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
		     + "model HMMTestFromStan(boolean[] flipsMeasured) {\n"
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
		     + "        //Commented lines are the lines missing in the Stan example. \n"
		     + "        //This seems to make sense, as there is no transition from state 0 to state 0,\n"
		     + "        //Just a possible state being picked based on the observed data.\n"
		     + "        //\n"
		     + "        //Forward execution is more complex now as st[0] is used but never set.\n"
		     + "        //we could add:\n"
		     + "        st[0] = categorical(m[0]).sample();  \n"
		     + "        \n"
		     + "        //Original code commented out below.\n"
		     + "        \n"
		     + "        //st[0] = 0;\n"
		     + "                \n"
		     + "        boolean[] flips = new boolean[samples];\n"
		     + "\n"
		     + "        //st[0] = categorical(m[st[0]]).sample(); \n"
		     + "        for(int i:[1..samples))\n"
		     + "            st[i] = categorical(m[st[i - 1]]).sample();\n"
		     + "\n"
		     + "\n"
		     + "        for(int j:[0..samples))\n"
		     + "            flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "\n"
		     + "        flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}