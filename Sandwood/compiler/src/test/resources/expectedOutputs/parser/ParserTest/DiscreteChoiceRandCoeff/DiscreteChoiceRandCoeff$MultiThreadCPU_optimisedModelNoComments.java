package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DiscreteChoiceRandCoeff$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DiscreteChoiceRandCoeff.State;
import org.sandwood.random.internal.Rng;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoiceRandCoeff$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[] guard$sample21categorical102$global;
		boolean[][] guard$sample21put101$global;
		boolean[][] guard$sample47categorical102$global;
		boolean[][][] guard$sample47put101$global;

		@Override
		public final void allocateScratch() {
			{
				int cv$max_j$var97 = 0;
				if((0 < state.noObs))
					cv$max_j$var97 = Math.max(0, state.noProducts);
				guard$sample21put101$global = new boolean[Math.max(0, state.noObs)][cv$max_j$var97];
			}
			guard$sample21categorical102$global = new boolean[Math.max(0, state.noObs)];
			{
				int cv$max_j$var97 = 0;
				if((0 < state.noObs))
					cv$max_j$var97 = Math.max(0, state.noProducts);
				int cv$max_i = Math.max(0, state.noObs);
				int cv$threadCount = threadCount();
				guard$sample47put101$global = new boolean[cv$threadCount][][];
				for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
					guard$sample47put101$global[cv$index] = new boolean[cv$max_i][cv$max_j$var97];
			}
			int cv$max_i = Math.max(0, state.noObs);
			int cv$threadCount = threadCount();
			guard$sample47categorical102$global = new boolean[cv$threadCount][];
			for(int cv$index = 0; cv$index < cv$threadCount; cv$index += 1)
				guard$sample47categorical102$global[cv$index] = new boolean[cv$max_i];
		}
	}


	public DiscreteChoiceRandCoeff$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample21(int var20) {
		state.ut[var20] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		for(int i = 0; i < state.noObs; i += 1)
			state.exped[i][var20] = Math.exp((state.ut[var20] - (state.beta[i] * state.Prices[i][var20])));
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				scratch.guard$sample21put101$global[i][j$var97] = false;
		}
		for(int i = 0; i < state.noObs; i += 1)
			scratch.guard$sample21put101$global[i][var20] = false;
		for(int i = 0; i < state.noObs; i += 1) {
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
				if(!scratch.guard$sample21put101$global[i][j$var97]) {
					scratch.guard$sample21put101$global[i][j$var97] = true;
					double reduceVar$sum$30 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
						reduceVar$sum$30 = (reduceVar$sum$30 + state.exped[i][cv$reduction82Index]);
					state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$30);
				}
			}
		}
		for(int i = 0; i < state.noObs; i += 1) {
			if(!scratch.guard$sample21put101$global[i][var20]) {
				scratch.guard$sample21put101$global[i][var20] = true;
				double reduceVar$sum$31 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
					reduceVar$sum$31 = (reduceVar$sum$31 + state.exped[i][cv$reduction82Index]);
				state.prob[i][var20] = (state.exped[i][var20] / reduceVar$sum$31);
			}
		}
	}

	private final void drawValueSample28() {
		state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
	}

	private final void drawValueSample34() {
		state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
	}

	private final void drawValueSample47(int var46, int threadID$cv$var46, Rng RNG$) {
		state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$)) + state.b);
		for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
			state.exped[var46][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[var46] * state.Prices[var46][j$var69])));
		boolean[][] guard$sample47put101 = scratch.guard$sample47put101$global[threadID$cv$var46];
		if((0 < state.noProducts)) {
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
				guard$sample47put101[var46][j$var97] = false;
		}
		for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
			guard$sample47put101[var46][j$var69] = false;
		if((0 < state.noProducts)) {
			for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
				if(!guard$sample47put101[var46][j$var97]) {
					guard$sample47put101[var46][j$var97] = true;
					double reduceVar$sum$32 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
						reduceVar$sum$32 = (reduceVar$sum$32 + state.exped[var46][cv$reduction82Index]);
					state.prob[var46][j$var97] = (state.exped[var46][j$var97] / reduceVar$sum$32);
				}
			}
		}
		for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
			if(!guard$sample47put101[var46][j$var69]) {
				guard$sample47put101[var46][j$var69] = true;
				double reduceVar$sum$33 = 0.0;
				for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
					reduceVar$sum$33 = (reduceVar$sum$33 + state.exped[var46][cv$reduction82Index]);
				state.prob[var46][j$var69] = (state.exped[var46][j$var69] / reduceVar$sum$33);
			}
		}
	}

	private final void inferSample21(int var20) {
		state.constrainedFlag$sample21[var20] = false;
		double cv$originalValue = state.ut[var20];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < state.noObs; i += 1)
				scratch.guard$sample21categorical102$global[i] = false;
			for(int i = 0; i < state.noObs; i += 1) {
				if(!scratch.guard$sample21categorical102$global[i]) {
					scratch.guard$sample21categorical102$global[i] = true;
					state.constrainedFlag$sample21[var20] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0.0 <= state.prob[i][state.choices[i]])) && (state.prob[i][state.choices[i]] <= 1.0))?Math.log(state.prob[i][state.choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < state.noObs; i += 1) {
				if(!scratch.guard$sample21categorical102$global[i]) {
					scratch.guard$sample21categorical102$global[i] = true;
					state.constrainedFlag$sample21[var20] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0.0 <= state.prob[i][state.choices[i]])) && (state.prob[i][state.choices[i]] <= 1.0))?Math.log(state.prob[i][state.choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample21[var20]) {
			state.ut[var20] = cv$proposedValue;
			for(int i = 0; i < state.noObs; i += 1)
				state.exped[i][var20] = Math.exp((state.ut[var20] - (state.beta[i] * state.Prices[i][var20])));
			for(int i = 0; i < state.noObs; i += 1) {
				for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
					scratch.guard$sample21put101$global[i][j$var97] = false;
			}
			for(int i = 0; i < state.noObs; i += 1)
				scratch.guard$sample21put101$global[i][var20] = false;
			for(int i = 0; i < state.noObs; i += 1) {
				for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
					if(!scratch.guard$sample21put101$global[i][j$var97]) {
						scratch.guard$sample21put101$global[i][j$var97] = true;
						double reduceVar$sum$20 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							reduceVar$sum$20 = (reduceVar$sum$20 + state.exped[i][cv$reduction82Index]);
						state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$20);
					}
				}
			}
			for(int i = 0; i < state.noObs; i += 1) {
				if(!scratch.guard$sample21put101$global[i][var20]) {
					scratch.guard$sample21put101$global[i][var20] = true;
					double reduceVar$sum$21 = 0.0;
					for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
						reduceVar$sum$21 = (reduceVar$sum$21 + state.exped[i][cv$reduction82Index]);
					state.prob[i][var20] = (state.exped[i][var20] / reduceVar$sum$21);
				}
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			for(int i = 0; i < state.noObs; i += 1)
				scratch.guard$sample21categorical102$global[i] = false;
			for(int i = 0; i < state.noObs; i += 1) {
				if(!scratch.guard$sample21categorical102$global[i]) {
					scratch.guard$sample21categorical102$global[i] = true;
					state.constrainedFlag$sample21[var20] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0.0 <= state.prob[i][state.choices[i]])) && (state.prob[i][state.choices[i]] <= 1.0))?Math.log(state.prob[i][state.choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			for(int i = 0; i < state.noObs; i += 1) {
				if(!scratch.guard$sample21categorical102$global[i]) {
					scratch.guard$sample21categorical102$global[i] = true;
					state.constrainedFlag$sample21[var20] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[i]) && (state.choices[i] < state.noProducts)) && (0.0 <= state.prob[i][state.choices[i]])) && (state.prob[i][state.choices[i]] <= 1.0))?Math.log(state.prob[i][state.choices[i]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.ut[var20] = cv$originalValue;
				for(int i = 0; i < state.noObs; i += 1)
					state.exped[i][var20] = Math.exp((state.ut[var20] - (state.beta[i] * state.Prices[i][var20])));
				for(int i = 0; i < state.noObs; i += 1) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
						scratch.guard$sample21put101$global[i][j$var97] = false;
				}
				for(int i = 0; i < state.noObs; i += 1)
					scratch.guard$sample21put101$global[i][var20] = false;
				for(int i = 0; i < state.noObs; i += 1) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
						if(!scratch.guard$sample21put101$global[i][j$var97]) {
							scratch.guard$sample21put101$global[i][j$var97] = true;
							double reduceVar$sum$23 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
								reduceVar$sum$23 = (reduceVar$sum$23 + state.exped[i][cv$reduction82Index]);
							state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$23);
						}
					}
				}
				for(int i = 0; i < state.noObs; i += 1) {
					if(!scratch.guard$sample21put101$global[i][var20]) {
						scratch.guard$sample21put101$global[i][var20] = true;
						double reduceVar$sum$24 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							reduceVar$sum$24 = (reduceVar$sum$24 + state.exped[i][cv$reduction82Index]);
						state.prob[i][var20] = (state.exped[i][var20] / reduceVar$sum$24);
					}
				}
			}
		}
	}

	private final void inferSample28() {
		state.constrainedFlag$sample28 = false;
		double cv$sum = 0.0;
		double cv$denominatorSquareSum = 0.0;
		boolean cv$sigmaNotFound = true;
		double cv$sigmaValue = 1.0;
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			if((state.fixedFlag$sample47 || state.constrainedFlag$sample47[var46])) {
				state.constrainedFlag$sample28 = true;
				cv$denominatorSquareSum = (cv$denominatorSquareSum + 1.0);
				cv$sum = (cv$sum + state.beta[var46]);
				if(cv$sigmaNotFound) {
					cv$sigmaValue = state.sigma;
					cv$sigmaNotFound = false;
				}
			}
		}
		if(state.constrainedFlag$sample28)
			state.b = Conjugates.sampleConjugateGaussianGaussian(state.RNG$, 0.0, 10.0, cv$sigmaValue, cv$sum, cv$denominatorSquareSum);
	}

	private final void inferSample34() {
		state.constrainedFlag$sample34 = false;
		double cv$sum = 0.0;
		int cv$count = 0;
		for(int var46 = 0; var46 < state.noObs; var46 += 1) {
			if((state.fixedFlag$sample47 || state.constrainedFlag$sample47[var46])) {
				state.constrainedFlag$sample34 = true;
				double cv$var35$diff = (state.b - state.beta[var46]);
				cv$sum = (cv$sum + (cv$var35$diff * cv$var35$diff));
				cv$count = (cv$count + 1);
			}
		}
		if(state.constrainedFlag$sample34)
			state.sigma = Conjugates.sampleConjugateInverseGammaGaussian(state.RNG$, 2.0, 2.0, cv$sum, cv$count);
	}

	private final void inferSample47(int var46, int threadID$cv$var46, Rng RNG$) {
		state.constrainedFlag$sample47[var46] = false;
		double cv$originalValue = state.beta[var46];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((cv$originalValue - state.b) / Math.sqrt(state.sigma))) - (Math.log(state.sigma) * 0.5)):Double.NEGATIVE_INFINITY);
			if((0 < state.noProducts)) {
				boolean[] guard$sample47categorical102 = scratch.guard$sample47categorical102$global[threadID$cv$var46];
				guard$sample47categorical102[var46] = false;
				if(!guard$sample47categorical102[var46]) {
					guard$sample47categorical102[var46] = true;
					state.constrainedFlag$sample47[var46] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var46]) && (state.choices[var46] < state.noProducts)) && (0.0 <= state.prob[var46][state.choices[var46]])) && (state.prob[var46][state.choices[var46]] <= 1.0))?Math.log(state.prob[var46][state.choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!guard$sample47categorical102[var46]) {
					guard$sample47categorical102[var46] = true;
					state.constrainedFlag$sample47[var46] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var46]) && (state.choices[var46] < state.noProducts)) && (0.0 <= state.prob[var46][state.choices[var46]])) && (state.prob[var46][state.choices[var46]] <= 1.0))?Math.log(state.prob[var46][state.choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample47[var46]) {
			{
				state.beta[var46] = cv$proposedValue;
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
					state.exped[var46][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[var46] * state.Prices[var46][j$var69])));
				boolean[][] guard$sample47put101 = scratch.guard$sample47put101$global[threadID$cv$var46];
				if((0 < state.noProducts)) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
						guard$sample47put101[var46][j$var97] = false;
				}
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
					guard$sample47put101[var46][j$var69] = false;
				if((0 < state.noProducts)) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
						if(!guard$sample47put101[var46][j$var97]) {
							guard$sample47put101[var46][j$var97] = true;
							double reduceVar$sum$25 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
								reduceVar$sum$25 = (reduceVar$sum$25 + state.exped[var46][cv$reduction82Index]);
							state.prob[var46][j$var97] = (state.exped[var46][j$var97] / reduceVar$sum$25);
						}
					}
				}
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if(!guard$sample47put101[var46][j$var69]) {
						guard$sample47put101[var46][j$var69] = true;
						double reduceVar$sum$26 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							reduceVar$sum$26 = (reduceVar$sum$26 + state.exped[var46][cv$reduction82Index]);
						state.prob[var46][j$var69] = (state.exped[var46][j$var69] / reduceVar$sum$26);
					}
				}
			}
			double cv$accumulatedProbabilities = ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((cv$proposedValue - state.b) / Math.sqrt(state.sigma))) - (Math.log(state.sigma) * 0.5)):Double.NEGATIVE_INFINITY);
			if((0 < state.noProducts)) {
				boolean[] guard$sample47categorical102 = scratch.guard$sample47categorical102$global[threadID$cv$var46];
				guard$sample47categorical102[var46] = false;
				if(!guard$sample47categorical102[var46]) {
					guard$sample47categorical102[var46] = true;
					state.constrainedFlag$sample47[var46] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var46]) && (state.choices[var46] < state.noProducts)) && (0.0 <= state.prob[var46][state.choices[var46]])) && (state.prob[var46][state.choices[var46]] <= 1.0))?Math.log(state.prob[var46][state.choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
				if(!guard$sample47categorical102[var46]) {
					guard$sample47categorical102[var46] = true;
					state.constrainedFlag$sample47[var46] = true;
					cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var46]) && (state.choices[var46] < state.noProducts)) && (0.0 <= state.prob[var46][state.choices[var46]])) && (state.prob[var46][state.choices[var46]] <= 1.0))?Math.log(state.prob[var46][state.choices[var46]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
				}
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(RNG$))) || Double.isNaN(cv$ratio))) {
				state.beta[var46] = cv$originalValue;
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
					state.exped[var46][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[var46] * state.Prices[var46][j$var69])));
				boolean[][] guard$sample47put101 = scratch.guard$sample47put101$global[threadID$cv$var46];
				if((0 < state.noProducts)) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1)
						guard$sample47put101[var46][j$var97] = false;
				}
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1)
					guard$sample47put101[var46][j$var69] = false;
				if((0 < state.noProducts)) {
					for(int j$var97 = 0; j$var97 < state.noProducts; j$var97 += 1) {
						if(!guard$sample47put101[var46][j$var97]) {
							guard$sample47put101[var46][j$var97] = true;
							double reduceVar$sum$28 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
								reduceVar$sum$28 = (reduceVar$sum$28 + state.exped[var46][cv$reduction82Index]);
							state.prob[var46][j$var97] = (state.exped[var46][j$var97] / reduceVar$sum$28);
						}
					}
				}
				for(int j$var69 = 0; j$var69 < state.noProducts; j$var69 += 1) {
					if(!guard$sample47put101[var46][j$var69]) {
						guard$sample47put101[var46][j$var69] = true;
						double reduceVar$sum$29 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							reduceVar$sum$29 = (reduceVar$sum$29 + state.exped[var46][cv$reduction82Index]);
						state.prob[var46][j$var69] = (state.exped[var46][j$var69] / reduceVar$sum$29);
					}
				}
			}
		}
	}

	private final void logProbabilityValue$sample103() {
		if(!state.fixedProbFlag$sample103) {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noObs; i += 1) {
				int cv$sampleValue = state.choices[i];
				double cv$distributionAccumulator = ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[i][cv$sampleValue])) && (state.prob[i][cv$sampleValue] <= 1.0))?Math.log(state.prob[i][cv$sampleValue]):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample103[i] = cv$distributionAccumulator;
			}
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample103 = (state.fixedFlag$sample21 && state.fixedFlag$sample47);
		} else {
			double cv$accumulator = 0.0;
			for(int i = 0; i < state.noObs; i += 1)
				cv$accumulator = (cv$accumulator + state.logProbability$sample103[i]);
			state.logProbability$choices = (state.logProbability$choices + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample21() {
		if(!state.fixedProbFlag$sample21) {
			double cv$sampleAccumulator = 0.0;
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.ut[var20] / 3.1622776601683795)) - 1.151292546497023);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				state.logProbability$sample21[var20] = cv$distributionAccumulator;
				if((0 < state.noObs))
					state.logProbability$prob = (state.logProbability$prob + cv$distributionAccumulator);
			}
			state.logProbability$ut = (state.logProbability$ut + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample21 = state.fixedFlag$sample21;
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
				double cv$sampleValue = state.logProbability$sample21[var20];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < state.noObs))
					state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
			}
			state.logProbability$ut = (state.logProbability$ut + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			if(state.fixedFlag$sample21)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	private final void logProbabilityValue$sample28() {
		if(!state.fixedProbFlag$sample28) {
			double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.b / 3.1622776601683795)) - 1.151292546497023);
			state.logProbability$b = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample28 = state.fixedFlag$sample28;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$b);
			if(state.fixedFlag$sample28)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$b);
		}
	}

	private final void logProbabilityValue$sample34() {
		if(!state.fixedProbFlag$sample34) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityInverseGamma(state.sigma, 2.0, 2.0);
			state.logProbability$sigma = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample34 = state.fixedFlag$sample34;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$sigma);
			if(state.fixedFlag$sample34)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$sigma);
		}
	}

	private final void logProbabilityValue$sample47() {
		if(!state.fixedProbFlag$sample47) {
			double cv$sampleAccumulator = 0.0;
			for(int var46 = 0; var46 < state.noObs; var46 += 1) {
				double cv$distributionAccumulator = ((0.0 < state.sigma)?(DistributionSampling.logProbabilityGaussian(((state.beta[var46] - state.b) / Math.sqrt(state.sigma))) - (Math.log(state.sigma) * 0.5)):Double.NEGATIVE_INFINITY);
				cv$sampleAccumulator = (cv$sampleAccumulator + cv$distributionAccumulator);
				state.logProbability$sample47[var46] = cv$distributionAccumulator;
				if((0 < state.noProducts))
					state.logProbability$prob = (state.logProbability$prob + cv$distributionAccumulator);
			}
			state.logProbability$beta = (state.logProbability$beta + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample47 = ((state.fixedFlag$sample47 && state.fixedFlag$sample28) && state.fixedFlag$sample34);
		} else {
			double cv$rvAccumulator = 0.0;
			for(int var46 = 0; var46 < state.noObs; var46 += 1) {
				double cv$sampleValue = state.logProbability$sample47[var46];
				cv$rvAccumulator = (cv$rvAccumulator + cv$sampleValue);
				if((0 < state.noProducts))
					state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
			}
			state.logProbability$beta = (state.logProbability$beta + cv$rvAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$rvAccumulator);
			if(state.fixedFlag$sample47)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$rvAccumulator);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample21)
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							state.ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		if(!state.fixedFlag$sample47)
			parallelFor(state.RNG$, 0, state.noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
				}
			);

		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						if((!state.fixedFlag$sample21 || !state.fixedFlag$sample47)) {
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
											state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
								}
							);
							double reduceVar$sum$34 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
								reduceVar$sum$34 = (reduceVar$sum$34 + state.exped[i][cv$reduction82Index]);
							double reduceVar$sum$34$1 = reduceVar$sum$34;
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
											state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$34$1);
								}
							);
						}
						state.choices[i] = DistributionSampling.sampleCategorical(RNG$1, state.prob[i], state.noProducts);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample21)
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							state.ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		if(!state.fixedFlag$sample47)
			parallelFor(state.RNG$, 0, state.noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
				}
			);

		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
							}
						);
						double reduceVar$sum$38 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							reduceVar$sum$38 = (reduceVar$sum$38 + state.exped[i][cv$reduction82Index]);
						double reduceVar$sum$38$1 = reduceVar$sum$38;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$38$1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample21)
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							state.ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		if(!state.fixedFlag$sample47)
			parallelFor(state.RNG$, 0, state.noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
				}
			);

		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
							}
						);
						double reduceVar$sum$35 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							reduceVar$sum$35 = (reduceVar$sum$35 + state.exped[i][cv$reduction82Index]);
						double reduceVar$sum$35$1 = reduceVar$sum$35;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$35$1);
							}
						);
						state.choices[i] = DistributionSampling.sampleCategorical(RNG$1, state.prob[i], state.noProducts);
					}
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample21)
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							state.ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		if(!state.fixedFlag$sample47)
			parallelFor(state.RNG$, 0, state.noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
				}
			);

		if((!state.fixedFlag$sample21 || !state.fixedFlag$sample47))
			parallelFor(state.RNG$, 0, state.noObs, 1,
				(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
							int i = index$i;
							int threadID$i = threadID$index$i;
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
											state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
								}
							);
							double reduceVar$sum$36 = 0.0;
							for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
								reduceVar$sum$36 = (reduceVar$sum$36 + state.exped[i][cv$reduction82Index]);
							double reduceVar$sum$36$1 = reduceVar$sum$36;
							parallelFor(RNG$1, 0, state.noProducts, 1,
								(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
									for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
											state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$36$1);
								}
							);
						}
				}
			);

	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample21)
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$var20, int forEnd$var20, int threadID$var20, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var20 = forStart$var20; var20 < forEnd$var20; var20 += 1)
							state.ut[var20] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		if(!state.fixedFlag$sample28)
			state.b = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		if(!state.fixedFlag$sample34)
			state.sigma = DistributionSampling.sampleInverseGamma(state.RNG$, 2.0, 2.0);
		if(!state.fixedFlag$sample47)
			parallelFor(state.RNG$, 0, state.noObs, 1,
				(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
							state.beta[var46] = ((Math.sqrt(state.sigma) * DistributionSampling.sampleGaussian(RNG$1)) + state.b);
				}
			);

		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
							}
						);
						double reduceVar$sum$37 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							reduceVar$sum$37 = (reduceVar$sum$37 + state.exped[i][cv$reduction82Index]);
						double reduceVar$sum$37$1 = reduceVar$sum$37;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$37$1);
							}
						);
					}
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(state.system$gibbsForward) {
			if(!state.fixedFlag$sample21) {
				for(int var20 = 0; var20 < state.noProducts; var20 += 1)
					inferSample21(var20);
			}
			if(!state.fixedFlag$sample28)
				inferSample28();
			if(!state.fixedFlag$sample34)
				inferSample34();
			if(!state.fixedFlag$sample47)
				parallelFor(state.RNG$, 0, state.noObs, 1,
					(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
								inferSample47(var46, threadID$var46, RNG$1);
					}
				);

		} else {
			if(!state.fixedFlag$sample47)
				parallelFor(state.RNG$, 0, state.noObs, 1,
					(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
						for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1)
								inferSample47(var46, threadID$var46, RNG$1);
					}
				);

			if(!state.fixedFlag$sample34)
				inferSample34();
			if(!state.fixedFlag$sample28)
				inferSample28();
			if(!state.fixedFlag$sample21) {
				for(int var20 = (state.noProducts - 1); var20 >= 0; var20 -= 1)
					inferSample21(var20);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int var20 = 0; var20 < state.noProducts; var20 += 1) {
			if(!state.constrainedFlag$sample21[var20])
				drawValueSample21(var20);
		}
		if(!state.constrainedFlag$sample28)
			drawValueSample28();
		if(!state.constrainedFlag$sample34)
			drawValueSample34();
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var46, int forEnd$var46, int threadID$var46, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var46 = forStart$var46; var46 < forEnd$var46; var46 += 1) {
						if(!state.constrainedFlag$sample47[var46])
							drawValueSample47(var46, threadID$var46, RNG$1);
					}
			}
		);
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$ut = 0.0;
		state.logProbability$prob = 0.0;
		if(!state.fixedProbFlag$sample21) {
			for(int var20 = 0; var20 < state.noProducts; var20 += 1)
				state.logProbability$sample21[var20] = Double.NaN;
		}
		if(!state.fixedProbFlag$sample28)
			state.logProbability$b = Double.NaN;
		if(!state.fixedProbFlag$sample34)
			state.logProbability$sigma = Double.NaN;
		state.logProbability$beta = 0.0;
		if(!state.fixedProbFlag$sample47) {
			for(int var46 = 0; var46 < state.noObs; var46 += 1)
				state.logProbability$sample47[var46] = Double.NaN;
		}
		state.logProbability$choices = 0.0;
		if(!state.fixedProbFlag$sample103) {
			for(int i = 0; i < state.noObs; i += 1)
				state.logProbability$sample103[i] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		for(int index$constrainedFlag$sample47$1 = 0; index$constrainedFlag$sample47$1 < state.constrainedFlag$sample47.length; index$constrainedFlag$sample47$1 += 1)
			state.constrainedFlag$sample47[index$constrainedFlag$sample47$1] = true;
		for(int index$constrainedFlag$sample21$1 = 0; index$constrainedFlag$sample21$1 < state.constrainedFlag$sample21.length; index$constrainedFlag$sample21$1 += 1)
			state.constrainedFlag$sample21[index$constrainedFlag$sample21$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample21)
			logProbabilityValue$sample21();
		if(state.fixedFlag$sample28)
			logProbabilityValue$sample28();
		if(state.fixedFlag$sample34)
			logProbabilityValue$sample34();
		if(state.fixedFlag$sample47)
			logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample21();
		logProbabilityValue$sample28();
		logProbabilityValue$sample34();
		logProbabilityValue$sample47();
		logProbabilityValue$sample103();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.choices[cv$index1] = state.ObsChoices[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$index$i, int forEnd$index$i, int threadID$index$i, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int index$i = forStart$index$i; index$i < forEnd$index$i; index$i += 1) {
						int i = index$i;
						int threadID$i = threadID$index$i;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var69, int forEnd$j$var69, int threadID$j$var69, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var69 = forStart$j$var69; j$var69 < forEnd$j$var69; j$var69 += 1)
										state.exped[i][j$var69] = Math.exp((state.ut[j$var69] - (state.beta[i] * state.Prices[i][j$var69])));
							}
						);
						double reduceVar$sum$39 = 0.0;
						for(int cv$reduction82Index = 0; cv$reduction82Index < state.noProducts; cv$reduction82Index += 1)
							reduceVar$sum$39 = (reduceVar$sum$39 + state.exped[i][cv$reduction82Index]);
						double reduceVar$sum$39$1 = reduceVar$sum$39;
						parallelFor(RNG$1, 0, state.noProducts, 1,
							(int forStart$j$var97, int forEnd$j$var97, int threadID$j$var97, org.sandwood.random.internal.Rng RNG$2) -> { 
								for(int j$var97 = forStart$j$var97; j$var97 < forEnd$j$var97; j$var97 += 1)
										state.prob[i][j$var97] = (state.exped[i][j$var97] / reduceVar$sum$39$1);
							}
						);
					}
			}
		);
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
		     + "\n"
		     + "model DiscreteChoiceRandCoeff(int noProducts, int noObs, int[] ObsChoices, int[][] Prices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = gaussian(0, 10).sample(noProducts);\n"
		     + "    //can we set the first element to 0? like ut[0] <~ 0\n"
		     + "\n"
		     + "    //priors for distribution of beta\n"
		     + "    double b = gaussian(0,10).sample();\n"
		     + "    double sigma =  inverseGamma(2,2).sample();\n"
		     + "\n"
		     + "    double[] beta = gaussian(b, sigma).sample(noObs);\n"
		     + "\n"
		     + "    int[] choices = new int[noObs];\n"
		     + "\n"
		     + "    for (int i:[0..noObs)){\n"
		     + "        // calculate choice probabilities for consumer i\n"
		     + "\n"
		     + "        double[] exped = new double[noProducts];\n"
		     + "        for(int j : [0..noProducts)) {\n"
		     + "            exped[j] = exp(ut[j]- beta[i]*Prices[i][j]);\n"
		     + "            }\n"
		     + "        double sum = reduce(exped, 0, (k, l) -> { return k + l; });\n"
		     + "        public double[] prob = new double[noProducts];\n"
		     + "        for (int j : [0..noProducts)) {\n"
		     + "            prob[j] = exped[j] / sum;\n"
		     + "        }\n"
		     + "        // emit choices of consumer i\n"
		     + "        choices[i] = categorical(prob).sample();\n"
		     + "                                }\n"
		     + "\n"
		     + "    // assert that generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}