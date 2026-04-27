package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.HMM_Paper$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.HMM_Paper.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class HMM_Paper$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
double[] cv$var28$countGlobal;
		double[] cv$var31$countGlobal;
		double[] cv$var52$stateProbabilityGlobal;
		double[] cv$var70$stateProbabilityGlobal;

		@Override
		public final void allocateScratch() {
			cv$var28$countGlobal = new double[state.nCoins];
			cv$var31$countGlobal = new double[state.nCoins];
			cv$var52$stateProbabilityGlobal = new double[state.nCoins];
			cv$var70$stateProbabilityGlobal = new double[state.nCoins];
		}
	}


	public HMM_Paper$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample28(int var27) {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.m[var27]);
	}

	private final void drawValueSample32() {
		DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
	}

	private final void drawValueSample47(int var45) {
		state.bias[var45] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void drawValueSample53() {
		state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
	}

	private final void drawValueSample71(int i) {
		state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
	}

	private final void inferSample28(int var27) {
		state.constrainedFlag$sample28[var27] = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.nCoins; cv$loopIndex += 1)
			scratch.cv$var28$countGlobal[cv$loopIndex] = 0.0;
		for(int i = 1; i < state.nFlips; i += 1) {
			if(((var27 == state.st[(i - 1)]) && (state.fixedFlag$sample71 || state.constrainedFlag$sample71[(i - 1)]))) {
				state.constrainedFlag$sample28[var27] = true;
				scratch.cv$var28$countGlobal[state.st[i]] = (scratch.cv$var28$countGlobal[state.st[i]] + 1.0);
			}
		}
		if(state.constrainedFlag$sample28[var27])
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var28$countGlobal, state.m[var27], state.nCoins);
	}

	private final void inferSample32() {
		state.constrainedFlag$sample32 = false;
		for(int cv$loopIndex = 0; cv$loopIndex < state.nCoins; cv$loopIndex += 1)
			scratch.cv$var31$countGlobal[cv$loopIndex] = 0.0;
		if((state.fixedFlag$sample53 || state.constrainedFlag$sample53)) {
			state.constrainedFlag$sample32 = true;
			scratch.cv$var31$countGlobal[state.st[0]] = (scratch.cv$var31$countGlobal[state.st[0]] + 1.0);
		}
		if(state.constrainedFlag$sample32)
			Conjugates.sampleConjugateDirichletCategorical(state.RNG$, state.v, scratch.cv$var31$countGlobal, state.initialCoin, state.nCoins);
	}

	private final void inferSample47(int var45) {
		state.constrainedFlag$sample47[var45] = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int j = 0; j < state.nFlips; j += 1) {
			if((var45 == state.st[j])) {
				state.constrainedFlag$sample47[var45] = true;
				cv$count = (cv$count + 1);
				if(state.flips[j])
					cv$sum = (cv$sum + 1);
			}
		}
		if(state.constrainedFlag$sample47[var45])
			state.bias[var45] = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void inferSample53() {
		state.constrainedFlag$sample53 = false;
		int cv$numStates = Math.max(0, state.nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			state.st[0] = cv$valuePos;
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.nCoins) && (0 < state.nCoins)) && (0.0 <= state.initialCoin[cv$valuePos])) && (state.initialCoin[cv$valuePos] <= 1.0))?Math.log(state.initialCoin[cv$valuePos]):Double.NEGATIVE_INFINITY);
			if(((1 < state.nFlips) && (state.fixedFlag$sample71 || state.constrainedFlag$sample71[0]))) {
				state.constrainedFlag$sample53 = true;
				double[] var68 = state.m[cv$valuePos];
				cv$accumulatedProbabilities = (((((((0.0 <= state.st[1]) && (state.st[1] < state.nCoins)) && (0 < state.nCoins)) && (0.0 <= var68[state.st[1]])) && (var68[state.st[1]] <= 1.0))?Math.log(var68[state.st[1]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			if((0 < state.nFlips)) {
				state.constrainedFlag$sample53 = true;
				double var84 = state.bias[cv$valuePos];
				cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[0]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			scratch.cv$var52$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample53) {
			double cv$logSum;
			double cv$lseMax = scratch.cv$var52$stateProbabilityGlobal[0];
			for(int cv$lseIndex = 1; cv$lseIndex < cv$numStates; cv$lseIndex += 1) {
				double cv$lseElementValue = scratch.cv$var52$stateProbabilityGlobal[cv$lseIndex];
				if((cv$lseMax < cv$lseElementValue))
					cv$lseMax = cv$lseElementValue;
			}
			if((cv$lseMax == Double.NEGATIVE_INFINITY))
				cv$logSum = Double.NEGATIVE_INFINITY;
			else {
				double cv$lseSum = 0.0;
				for(int cv$lseIndex = 0; cv$lseIndex < cv$numStates; cv$lseIndex += 1)
					cv$lseSum = (cv$lseSum + Math.exp((scratch.cv$var52$stateProbabilityGlobal[cv$lseIndex] - cv$lseMax)));
				cv$logSum = (Math.log(cv$lseSum) + cv$lseMax);
			}
			if((cv$logSum == Double.NEGATIVE_INFINITY)) {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var52$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var52$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var52$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var52$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void inferSample71(int i) {
		state.constrainedFlag$sample71[(i - 1)] = false;
		int cv$numStates = Math.max(0, state.nCoins);
		for(int cv$valuePos = 0; cv$valuePos < cv$numStates; cv$valuePos += 1) {
			state.st[i] = cv$valuePos;
			double[] var68 = state.m[state.st[(i - 1)]];
			double cv$accumulatedProbabilities = (((((cv$valuePos < state.nCoins) && (0 < state.nCoins)) && (0.0 <= var68[cv$valuePos])) && (var68[cv$valuePos] <= 1.0))?Math.log(var68[cv$valuePos]):Double.NEGATIVE_INFINITY);
			int index$i$2_2 = (i + 1);
			if(((index$i$2_2 < state.nFlips) && (state.fixedFlag$sample71 || state.constrainedFlag$sample71[(index$i$2_2 - 1)]))) {
				double[] sc$var68$1 = state.m[cv$valuePos];
				cv$accumulatedProbabilities = (((((((0.0 <= state.st[index$i$2_2]) && (state.st[index$i$2_2] < state.nCoins)) && (0 < state.nCoins)) && (0.0 <= sc$var68$1[state.st[index$i$2_2]])) && (sc$var68$1[state.st[index$i$2_2]] <= 1.0))?Math.log(sc$var68$1[state.st[index$i$2_2]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			state.constrainedFlag$sample71[(i - 1)] = true;
			double var84 = state.bias[cv$valuePos];
			cv$accumulatedProbabilities = ((((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[i]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			scratch.cv$var70$stateProbabilityGlobal[cv$valuePos] = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample71[(i - 1)]) {
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
					scratch.cv$var70$stateProbabilityGlobal[cv$indexName] = (1.0 / cv$numStates);
			} else {
				for(int cv$indexName = 0; cv$indexName < cv$numStates; cv$indexName += 1)
					scratch.cv$var70$stateProbabilityGlobal[cv$indexName] = Math.exp((scratch.cv$var70$stateProbabilityGlobal[cv$indexName] - cv$logSum));
			}
			for(int cv$indexName = cv$numStates; cv$indexName < scratch.cv$var70$stateProbabilityGlobal.length; cv$indexName += 1)
				scratch.cv$var70$stateProbabilityGlobal[cv$indexName] = Double.NEGATIVE_INFINITY;
			state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, scratch.cv$var70$stateProbabilityGlobal, cv$numStates);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$sampleAccumulator = 0.0;
			for(int var27 = 0; var27 < state.nCoins; var27 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityDirichlet(state.m[var27], state.v, state.nCoins));
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

	private final void logProbabilityValue$sample32() {
		if(!state.fixedProbFlag$sample32) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityDirichlet(state.initialCoin, state.v, state.nCoins);
			state.logProbability$initialCoin = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample32)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample32 = state.fixedFlag$sample32;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$initialCoin);
			if(state.fixedFlag$sample32)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$initialCoin);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!state.fixedProbFlag$sample47) {
			double cv$sampleAccumulator = 0.0;
			for(int var45 = 0; var45 < state.nCoins; var45 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + DistributionSampling.logProbabilityBeta(state.bias[var45], 1.0, 1.0));
			state.logProbability$var46 = cv$sampleAccumulator;
			state.logProbability$bias = (state.logProbability$bias + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample47 = state.fixedFlag$sample47;
		} else {
			state.logProbability$bias = (state.logProbability$bias + state.logProbability$var46);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var46);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var46);
		}
	}

	private final void logProbabilityValue$sample53() {
		if(!state.fixedProbFlag$sample53) {
			int cv$sampleValue = state.st[0];
			double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.nCoins)) && (0 < state.nCoins)) && (0.0 <= state.initialCoin[cv$sampleValue])) && (state.initialCoin[cv$sampleValue] <= 1.0))?Math.log(state.initialCoin[cv$sampleValue]):Double.NEGATIVE_INFINITY);
			state.logProbability$var52 = cv$distributionAccumulator;
			state.logProbability$st = (state.logProbability$st + cv$distributionAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample53)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample53 = (state.fixedFlag$sample53 && state.fixedFlag$sample32);
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
			for(int i = 1; i < state.nFlips; i += 1) {
				int cv$sampleValue = state.st[i];
				double[] var68 = state.m[state.st[(i - 1)]];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.nCoins)) && (0 < state.nCoins)) && (0.0 <= var68[cv$sampleValue])) && (var68[cv$sampleValue] <= 1.0))?Math.log(var68[cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample71[(i - 1)] = cv$distributionAccumulator;
			}
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample71 = ((state.fixedFlag$sample71 && state.fixedFlag$sample28) && state.fixedFlag$sample53);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 1; i < state.nFlips; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample71[(i - 1)]);
			state.logProbability$st = (state.logProbability$st + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample71)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample87() {
		if(!state.fixedProbFlag$sample87) {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.nFlips; j += 1) {
				double var84 = state.bias[state.st[j]];
				double cv$distributionAccumulator = (((0.0 <= var84) && (var84 <= 1.0))?Math.log((state.flips[j]?var84:(1.0 - var84))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample87[j] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample87 = ((state.fixedFlag$sample47 && state.fixedFlag$sample53) && state.fixedFlag$sample71);
		} else {
			double cv$accumulator = 0.0;
			for(int j = 0; j < state.nFlips; j += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample87[j]);
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < state.nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.m[var27]);
		}
		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		if(!state.fixedFlag$sample47) {
			for(int var45 = 0; var45 < state.nCoins; var45 += 1)
				state.bias[var45] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
		for(int j = 0; j < state.nFlips; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[j]]);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < state.nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.m[var27]);
		}
		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		if(!state.fixedFlag$sample47) {
			for(int var45 = 0; var45 < state.nCoins; var45 += 1)
				state.bias[var45] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < state.nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.m[var27]);
		}
		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		if(!state.fixedFlag$sample47) {
			for(int var45 = 0; var45 < state.nCoins; var45 += 1)
				state.bias[var45] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
		for(int j = 0; j < state.nFlips; j += 1)
			state.flips[j] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias[state.st[j]]);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < state.nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.m[var27]);
		}
		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		if(!state.fixedFlag$sample47) {
			for(int var45 = 0; var45 < state.nCoins; var45 += 1)
				state.bias[var45] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample28) {
			for(int var27 = 0; var27 < state.nCoins; var27 += 1)
				DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.m[var27]);
		}
		if(!state.fixedFlag$sample32)
			DistributionSampling.sampleDirichlet(state.RNG$, state.v, state.nCoins, state.initialCoin);
		if(!state.fixedFlag$sample47) {
			for(int var45 = 0; var45 < state.nCoins; var45 += 1)
				state.bias[var45] = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		}
		if(!state.fixedFlag$sample53)
			state.st[0] = DistributionSampling.sampleCategorical(state.RNG$, state.initialCoin, state.nCoins);
		if(!state.fixedFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.st[i] = DistributionSampling.sampleCategorical(state.RNG$, state.m[state.st[(i - 1)]], state.nCoins);
		}
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample28) {
				for(int var27 = 0; var27 < state.nCoins; var27 += 1)
					inferSample28(var27);
			}
			if(!state.fixedFlag$sample32)
				inferSample32();
			if(!state.fixedFlag$sample47) {
				for(int var45 = 0; var45 < state.nCoins; var45 += 1)
					inferSample47(var45);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			if(!state.fixedFlag$sample71) {
				for(int i = 1; i < state.nFlips; i += 1)
					inferSample71(i);
			}
		} else {
			if(!state.fixedFlag$sample71) {
				for(int i = (state.nFlips - 1); i >= 1; i -= 1)
					inferSample71(i);
			}
			if(!state.fixedFlag$sample53)
				inferSample53();
			if(!state.fixedFlag$sample47) {
				for(int var45 = (state.nCoins - 1); var45 >= 0; var45 -= 1)
					inferSample47(var45);
			}
			if(!state.fixedFlag$sample32)
				inferSample32();
			if(!state.fixedFlag$sample28) {
				for(int var27 = (state.nCoins - 1); var27 >= 0; var27 -= 1)
					inferSample28(var27);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var27 = 0; var27 < state.nCoins; var27 += 1) {
			if(!state.constrainedFlag$sample28[var27])
				drawValueSample28(var27);
		}
		if(!state.constrainedFlag$sample32)
			drawValueSample32();
		for(int var45 = 0; var45 < state.nCoins; var45 += 1) {
			if(!state.constrainedFlag$sample47[var45])
				drawValueSample47(var45);
		}
		if(!state.constrainedFlag$sample53)
			drawValueSample53();
		for(int i = 1; i < state.nFlips; i += 1) {
			if(!state.constrainedFlag$sample71[(i - 1)])
				drawValueSample71(i);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$m = 0.0;
		if(!state.fixedProbFlag$sample28)
			state.logProbability$var28 = Double.NaN;
		if(!state.fixedProbFlag$sample32)
			state.logProbability$initialCoin = Double.NaN;
		state.logProbability$bias = 0.0;
		if(!state.fixedProbFlag$sample47)
			state.logProbability$var46 = Double.NaN;
		state.logProbability$st = 0.0;
		if(!state.fixedProbFlag$sample53)
			state.logProbability$var52 = Double.NaN;
		if(!state.fixedProbFlag$sample71) {
			for(int i = 1; i < state.nFlips; i += 1)
				state.logProbability$sample71[(i - 1)] = Double.NaN;
		}
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample87) {
			for(int j = 0; j < state.nFlips; j += 1)
				state.logProbability$sample87[j] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int var13 = 0; var13 < state.nCoins; var13 += 1)
			state.v[var13] = 0.1;
		state.nFlips = state.length$measured;
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < state.constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			state.constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
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
		if(state.fixedFlag$sample32)
			logProbabilityValue$sample32();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		if(state.fixedFlag$sample53)
			logProbabilityValue$sample53();
		if(state.fixedFlag$sample71)
			logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample32();
		logProbabilityValue$sample47();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample28();
		logProbabilityValue$sample32();
		logProbabilityValue$sample47();
		logProbabilityValue$sample53();
		logProbabilityValue$sample71();
		logProbabilityValue$sample87();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.flips.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.flips[cv$index1] = state.measured[cv$index1];
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
		     + "model HMM_Paper(boolean[] measured, int nCoins) {\n"
		     + "  //Construct a transistion matrix m.\n"
		     + "  double[] v = new double[nCoins] <~ 0.1;\n"
		     + "  double[][] m = dirichlet(v).sample(nCoins);\n"
		     + "  \n"
		     + "  //Construct weighting for which coin to start at.\n"
		     + "  double[] initialCoin = dirichlet(v).sample;\n"
		     + "    \n"
		     + "  //Construct biases for each coin    \n"
		     + "  double[] bias = beta(1.0, 1.0).sample(nCoins);\n"
		     + "\n"
		     + "  //Allocate space to record which coin is flipped\n"
		     + "  int nFlips = measured.length;\n"
		     + "  int[] st = new int[nFlips];\n"
		     + "\n"
		     + "  //Calculate the movements between coins        \n"
		     + "  st[0] = categorical(initialCoin).sample();\n"
		     + "  for (int i: [1..nFlips) )\n"
		     + "    st[i] = categorical(m[st[i - 1]]).sample();\n"
		     + "\n"
		     + "  //Flip the coins\n"
		     + "  boolean[] flips = new boolean[nFlips];\n"
		     + "  for (int j: [0..nFlips) )\n"
		     + "    flips[j] = bernoulli(bias[st[j]]).sample();\n"
		     + "    \n"
		     + "  //Assert that the flips match the measured data.\n"
		     + "  flips.observe(measured);\n"
		     + "}\n"
		     + "";
	}
}