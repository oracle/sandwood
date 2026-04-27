package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DiscreteChoice$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DiscreteChoice.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoice$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[] guard$sample24put65$global;

		@Override
		public final void allocateScratch() {
			guard$sample24put65$global = new boolean[Math.max(0, state.noProducts)];
		}
	}


	public DiscreteChoice$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample24(int i$var18) {
		state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		state.exped[i$var18] = Math.exp(state.ut[i$var18]);
		double reduceVar$sum$13 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$13 = (reduceVar$sum$13 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$13;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			scratch.guard$sample24put65$global[i$var61] = false;
		scratch.guard$sample24put65$global[i$var18] = false;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
			if(!scratch.guard$sample24put65$global[i$var61]) {
				scratch.guard$sample24put65$global[i$var61] = true;
				state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$13);
			}
		}
		if(!scratch.guard$sample24put65$global[i$var18]) {
			scratch.guard$sample24put65$global[i$var18] = true;
			state.prob[i$var18] = (state.exped[i$var18] / reduceVar$sum$13);
		}
	}

	private final void inferSample24(int i$var18) {
		state.constrainedFlag$sample24[(i$var18 - 1)] = false;
		double cv$originalValue = state.ut[i$var18];
		double cv$originalProbability;
		double cv$var = (((cv$originalValue < 0)?(-cv$originalValue):cv$originalValue) * 40.0);
		if((cv$var < 0.01))
			cv$var = 0.01;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + cv$originalValue);
		{
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$originalValue / 3.1622776601683795)) - 1.151292546497023);
			for(int var76 = 0; var76 < state.noObs; var76 += 1) {
				state.constrainedFlag$sample24[(i$var18 - 1)] = true;
				cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			cv$originalProbability = cv$accumulatedProbabilities;
		}
		if(state.constrainedFlag$sample24[(i$var18 - 1)]) {
			state.ut[i$var18] = cv$proposedValue;
			state.exped[i$var18] = Math.exp(state.ut[i$var18]);
			double reduceVar$sum$10 = 0.0;
			for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
				reduceVar$sum$10 = (reduceVar$sum$10 + state.exped[cv$reduction44Index]);
			state.sum = reduceVar$sum$10;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
				scratch.guard$sample24put65$global[i$var61] = false;
			scratch.guard$sample24put65$global[i$var18] = false;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
				if(!scratch.guard$sample24put65$global[i$var61]) {
					scratch.guard$sample24put65$global[i$var61] = true;
					state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$10);
				}
			}
			if(!scratch.guard$sample24put65$global[i$var18]) {
				scratch.guard$sample24put65$global[i$var18] = true;
				state.prob[i$var18] = (state.exped[i$var18] / reduceVar$sum$10);
			}
			double cv$accumulatedProbabilities = (DistributionSampling.logProbabilityGaussian((cv$proposedValue / 3.1622776601683795)) - 1.151292546497023);
			for(int var76 = 0; var76 < state.noObs; var76 += 1) {
				state.constrainedFlag$sample24[(i$var18 - 1)] = true;
				cv$accumulatedProbabilities = ((((((0.0 <= state.choices[var76]) && (state.choices[var76] < state.noProducts)) && (0.0 <= state.prob[state.choices[var76]])) && (state.prob[state.choices[var76]] <= 1.0))?Math.log(state.prob[state.choices[var76]]):Double.NEGATIVE_INFINITY) + cv$accumulatedProbabilities);
			}
			double cv$ratio = (cv$accumulatedProbabilities - cv$originalProbability);
			if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio))) {
				state.ut[i$var18] = cv$originalValue;
				state.exped[i$var18] = Math.exp(state.ut[i$var18]);
				double reduceVar$sum$12 = 0.0;
				for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
					reduceVar$sum$12 = (reduceVar$sum$12 + state.exped[cv$reduction44Index]);
				state.sum = reduceVar$sum$12;
				for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
					scratch.guard$sample24put65$global[i$var61] = false;
				scratch.guard$sample24put65$global[i$var18] = false;
				for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
					if(!scratch.guard$sample24put65$global[i$var61]) {
						scratch.guard$sample24put65$global[i$var61] = true;
						state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$12);
					}
				}
				if(!scratch.guard$sample24put65$global[i$var18]) {
					scratch.guard$sample24put65$global[i$var18] = true;
					state.prob[i$var18] = (state.exped[i$var18] / reduceVar$sum$12);
				}
			}
		}
	}

	private final void logProbabilityValue$sample24() {
		if(!state.fixedProbFlag$sample24) {
			double cv$accumulator = 0.0;
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
				double cv$distributionAccumulator = (DistributionSampling.logProbabilityGaussian((state.ut[i$var18] / 3.1622776601683795)) - 1.151292546497023);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$sample24[(i$var18 - 1)] = cv$distributionAccumulator;
				state.logProbability$exped = (state.logProbability$exped + cv$distributionAccumulator);
				state.logProbability$sum = (state.logProbability$sum + cv$distributionAccumulator);
				state.logProbability$prob = (state.logProbability$prob + cv$distributionAccumulator);
			}
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample24 = state.fixedFlag$sample24;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
				double cv$sampleValue = state.logProbability$sample24[(i$var18 - 1)];
				cv$accumulator = (cv$accumulator + cv$sampleValue);
				state.logProbability$exped = (state.logProbability$exped + cv$sampleValue);
				state.logProbability$sum = (state.logProbability$sum + cv$sampleValue);
				state.logProbability$prob = (state.logProbability$prob + cv$sampleValue);
			}
			state.logProbability$ut = (state.logProbability$ut + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			if(state.fixedFlag$sample24)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample78() {
		if(!state.fixedProbFlag$sample78) {
			double cv$sampleAccumulator = 0.0;
			for(int var76 = 0; var76 < state.noObs; var76 += 1) {
				int cv$sampleValue = state.choices[var76];
				cv$sampleAccumulator = (cv$sampleAccumulator + ((((((0.0 <= cv$sampleValue) && (cv$sampleValue < state.noProducts)) && (0 < state.noProducts)) && (0.0 <= state.prob[cv$sampleValue])) && (state.prob[cv$sampleValue] <= 1.0))?Math.log(state.prob[cv$sampleValue]):Double.NEGATIVE_INFINITY));
			}
			state.logProbability$var77 = cv$sampleAccumulator;
			state.logProbability$choices = (state.logProbability$choices + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample78 = state.fixedFlag$sample24;
		} else {
			state.logProbability$choices = (state.logProbability$choices + state.logProbability$var77);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var77);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var77);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample24) {
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
							state.ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
							state.exped[i$var36] = Math.exp(state.ut[i$var36]);
				}
			);
			double reduceVar$sum$14 = 0.0;
			for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
				reduceVar$sum$14 = (reduceVar$sum$14 + state.exped[cv$reduction44Index]);
			state.sum = reduceVar$sum$14;
			double reduceVar$sum$14$1 = reduceVar$sum$14;
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
							state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$14$1);
				}
			);
		}
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
						state.choices[var76] = DistributionSampling.sampleCategorical(RNG$1, state.prob, state.noProducts);
			}
		);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample24)
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
							state.ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		double reduceVar$sum$18 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$18 = (reduceVar$sum$18 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$18;
		double reduceVar$sum$18$1 = reduceVar$sum$18;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$18$1);
			}
		);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample24)
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
							state.ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		double reduceVar$sum$15 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$15 = (reduceVar$sum$15 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$15;
		double reduceVar$sum$15$1 = reduceVar$sum$15;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$15$1);
			}
		);
		parallelFor(state.RNG$, 0, state.noObs, 1,
			(int forStart$var76, int forEnd$var76, int threadID$var76, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int var76 = forStart$var76; var76 < forEnd$var76; var76 += 1)
						state.choices[var76] = DistributionSampling.sampleCategorical(RNG$1, state.prob, state.noProducts);
			}
		);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample24) {
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
							state.ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
							state.exped[i$var36] = Math.exp(state.ut[i$var36]);
				}
			);
			double reduceVar$sum$16 = 0.0;
			for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
				reduceVar$sum$16 = (reduceVar$sum$16 + state.exped[cv$reduction44Index]);
			state.sum = reduceVar$sum$16;
			double reduceVar$sum$16$1 = reduceVar$sum$16;
			parallelFor(state.RNG$, 0, state.noProducts, 1,
				(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
							state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$16$1);
				}
			);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample24)
			parallelFor(state.RNG$, 1, state.noProducts, 1,
				(int forStart$i$var18, int forEnd$i$var18, int threadID$i$var18, org.sandwood.random.internal.Rng RNG$1) -> { 
					for(int i$var18 = forStart$i$var18; i$var18 < forEnd$i$var18; i$var18 += 1)
							state.ut[i$var18] = (DistributionSampling.sampleGaussian(RNG$1) * 3.1622776601683795);
				}
			);

		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		double reduceVar$sum$17 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$17 = (reduceVar$sum$17 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$17;
		double reduceVar$sum$17$1 = reduceVar$sum$17;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$17$1);
			}
		);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample24) {
			if(state.system$gibbsForward) {
				for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
					inferSample24(i$var18);
			} else {
				for(int i$var18 = (state.noProducts - 1); i$var18 >= 1; i$var18 -= 1)
					inferSample24(i$var18);
			}
		}
		state.system$gibbsForward = !state.system$gibbsForward;
		for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1) {
			if(!state.constrainedFlag$sample24[(i$var18 - 1)])
				drawValueSample24(i$var18);
		}
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$ut = 0.0;
		state.logProbability$exped = 0.0;
		state.logProbability$sum = 0.0;
		state.logProbability$prob = 0.0;
		if(!state.fixedProbFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.logProbability$sample24[(i$var18 - 1)] = Double.NaN;
		}
		state.logProbability$choices = 0.0;
		if(!state.fixedProbFlag$sample78)
			state.logProbability$var77 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.ut[0] = 0.0;
		for(int index$constrainedFlag$sample24$1 = 0; index$constrainedFlag$sample24$1 < state.constrainedFlag$sample24.length; index$constrainedFlag$sample24$1 += 1)
			state.constrainedFlag$sample24[index$constrainedFlag$sample24$1] = true;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample24)
			logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample24();
		logProbabilityValue$sample78();
	}

	@Override
	public final void propagateObservedValues() {
		int cv$length1 = state.choices.length;
		for(int cv$index1 = 0; cv$index1 < cv$length1; cv$index1 += 1)
			state.choices[cv$index1] = state.ObsChoices[cv$index1];
	}

	@Override
	public final void setIntermediates() {
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var36, int forEnd$i$var36, int threadID$i$var36, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var36 = forStart$i$var36; i$var36 < forEnd$i$var36; i$var36 += 1)
						state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			}
		);
		double reduceVar$sum$19 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$19 = (reduceVar$sum$19 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$19;
		double reduceVar$sum$19$1 = reduceVar$sum$19;
		parallelFor(state.RNG$, 0, state.noProducts, 1,
			(int forStart$i$var61, int forEnd$i$var61, int threadID$i$var61, org.sandwood.random.internal.Rng RNG$1) -> { 
				for(int i$var61 = forStart$i$var61; i$var61 < forEnd$i$var61; i$var61 += 1)
						state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$19$1);
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
		     + "public model DiscreteChoice(int noProducts, int noObs, int[] ObsChoices) {\n"
		     + "    // we just need an uninformative prior for utility intercepts\n"
		     + "\n"
		     + "    // draw utilities\n"
		     + "    double[] ut = new double[noProducts];\n"
		     + "    ut[0] = 0.0;\n"
		     + "    for(int i=1; i<noProducts; i++) {\n"
		     + "        ut[i]= gaussian(0, 10).sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    // calculate choice probabilities\n"
		     + "    double[] exped = new double[noProducts];\n"
		     + "    for(int i : [0..noProducts)) {\n"
		     + "        exped[i] = exp(ut[i]);\n"
		     + "    }\n"
		     + "    double sum = reduce(exped, 0, (i, j) -> { return i + j; });\n"
		     + "    double[] prob = new double[noProducts];\n"
		     + "    for (int i : [0..noProducts)) {\n"
		     + "        prob[i] = exped[i] / sum;\n"
		     + "    }\n"
		     + "    // draw consumer choices according to the calculated probabilities\n"
		     + "    int[] choices = categorical(prob).sample(noObs);\n"
		     + "\n"
		     + "    // assert generated choices match observed choices\n"
		     + "    choices.observe(ObsChoices);\n"
		     + "}";
	}
}