package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.DiscreteChoice$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.DiscreteChoice.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class DiscreteChoice$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {
boolean[] guard$sample24put65$global;

		@Override
		public final void allocateScratch() {
			guard$sample24put65$global = new boolean[Math.max(0, state.noProducts)];
		}
	}


	public DiscreteChoice$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample24(int i$var18) {
		state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		state.exped[i$var18] = Math.exp(state.ut[i$var18]);
		double reduceVar$sum$3 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$3 = (reduceVar$sum$3 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$3;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			scratch.guard$sample24put65$global[i$var61] = false;
		scratch.guard$sample24put65$global[i$var18] = false;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
			if(!scratch.guard$sample24put65$global[i$var61]) {
				scratch.guard$sample24put65$global[i$var61] = true;
				state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$3);
			}
		}
		if(!scratch.guard$sample24put65$global[i$var18]) {
			scratch.guard$sample24put65$global[i$var18] = true;
			state.prob[i$var18] = (state.exped[i$var18] / reduceVar$sum$3);
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
			double reduceVar$sum$0 = 0.0;
			for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
				reduceVar$sum$0 = (reduceVar$sum$0 + state.exped[cv$reduction44Index]);
			state.sum = reduceVar$sum$0;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
				scratch.guard$sample24put65$global[i$var61] = false;
			scratch.guard$sample24put65$global[i$var18] = false;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
				if(!scratch.guard$sample24put65$global[i$var61]) {
					scratch.guard$sample24put65$global[i$var61] = true;
					state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$0);
				}
			}
			if(!scratch.guard$sample24put65$global[i$var18]) {
				scratch.guard$sample24put65$global[i$var18] = true;
				state.prob[i$var18] = (state.exped[i$var18] / reduceVar$sum$0);
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
				double reduceVar$sum$2 = 0.0;
				for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
					reduceVar$sum$2 = (reduceVar$sum$2 + state.exped[cv$reduction44Index]);
				state.sum = reduceVar$sum$2;
				for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
					scratch.guard$sample24put65$global[i$var61] = false;
				scratch.guard$sample24put65$global[i$var18] = false;
				for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1) {
					if(!scratch.guard$sample24put65$global[i$var61]) {
						scratch.guard$sample24put65$global[i$var61] = true;
						state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$2);
					}
				}
				if(!scratch.guard$sample24put65$global[i$var18]) {
					scratch.guard$sample24put65$global[i$var18] = true;
					state.prob[i$var18] = (state.exped[i$var18] / reduceVar$sum$2);
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
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
				state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			double reduceVar$sum$4 = 0.0;
			for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
				reduceVar$sum$4 = (reduceVar$sum$4 + state.exped[cv$reduction44Index]);
			state.sum = reduceVar$sum$4;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
				state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$4);
		}
		for(int var76 = 0; var76 < state.noObs; var76 += 1)
			state.choices[var76] = DistributionSampling.sampleCategorical(state.RNG$, state.prob, state.noProducts);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
			state.exped[i$var36] = Math.exp(state.ut[i$var36]);
		double reduceVar$sum$8 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$8 = (reduceVar$sum$8 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$8;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$8);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
			state.exped[i$var36] = Math.exp(state.ut[i$var36]);
		double reduceVar$sum$5 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$5 = (reduceVar$sum$5 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$5;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$5);
		for(int var76 = 0; var76 < state.noObs; var76 += 1)
			state.choices[var76] = DistributionSampling.sampleCategorical(state.RNG$, state.prob, state.noProducts);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
			for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
				state.exped[i$var36] = Math.exp(state.ut[i$var36]);
			double reduceVar$sum$6 = 0.0;
			for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
				reduceVar$sum$6 = (reduceVar$sum$6 + state.exped[cv$reduction44Index]);
			state.sum = reduceVar$sum$6;
			for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
				state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$6);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample24) {
			for(int i$var18 = 1; i$var18 < state.noProducts; i$var18 += 1)
				state.ut[i$var18] = (DistributionSampling.sampleGaussian(state.RNG$) * 3.1622776601683795);
		}
		for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
			state.exped[i$var36] = Math.exp(state.ut[i$var36]);
		double reduceVar$sum$7 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$7 = (reduceVar$sum$7 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$7;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$7);
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
		for(int i$var36 = 0; i$var36 < state.noProducts; i$var36 += 1)
			state.exped[i$var36] = Math.exp(state.ut[i$var36]);
		double reduceVar$sum$9 = 0.0;
		for(int cv$reduction44Index = 0; cv$reduction44Index < state.noProducts; cv$reduction44Index += 1)
			reduceVar$sum$9 = (reduceVar$sum$9 + state.exped[cv$reduction44Index]);
		state.sum = reduceVar$sum$9;
		for(int i$var61 = 0; i$var61 < state.noProducts; i$var61 += 1)
			state.prob[i$var61] = (state.exped[i$var61] / reduceVar$sum$9);
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