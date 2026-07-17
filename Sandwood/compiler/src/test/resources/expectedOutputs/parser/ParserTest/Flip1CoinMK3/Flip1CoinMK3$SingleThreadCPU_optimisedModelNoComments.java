package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK3$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK3.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK3$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK3$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample6() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample6() {
		state.constrainedFlag$sample6 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int i$var21 = 0; i$var21 < state.samples; i$var21 += 1) {
			state.constrainedFlag$sample6 = true;
			cv$count = (cv$count + 1);
			if(state.flips[i$var21])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample6)
			state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample23() {
		if(!state.fixedProbFlag$sample23) {
			double cv$accumulator = 0.0;
			for(int i$var21 = 0; i$var21 < state.samples; i$var21 += 1) {
				double cv$distributionAccumulator = (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips[i$var21]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY);
				cv$accumulator = (cv$accumulator + cv$distributionAccumulator);
				state.logProbability$bernoulli[i$var21] = cv$distributionAccumulator;
				state.logProbability$sample23[i$var21] = cv$distributionAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
			state.fixedProbFlag$sample23 = state.fixedFlag$sample6;
		} else {
			double cv$accumulator = 0.0;
			for(int i$var21 = 0; i$var21 < state.samples; i$var21 += 1) {
				double cv$rvAccumulator = state.logProbability$sample23[i$var21];
				cv$accumulator = (cv$accumulator + cv$rvAccumulator);
				state.logProbability$bernoulli[i$var21] = cv$rvAccumulator;
			}
			state.logProbability$flips = (state.logProbability$flips + cv$accumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
		}
	}

	private final void logProbabilityValue$sample6() {
		if(!state.fixedProbFlag$sample6) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias, 1.0, 1.0);
			state.logProbability$bias = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample6 = state.fixedFlag$sample6;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			if(state.fixedFlag$sample6)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var21 = 0; i$var21 < state.samples; i$var21 += 1)
			state.flips[i$var21] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i$var21 = 0; i$var21 < state.samples; i$var21 += 1)
			state.flips[i$var21] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample6)
			inferSample6();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample6)
			drawValueSample6();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample6)
			state.logProbability$bias = Double.NaN;
		for(int i$var21 = 0; i$var21 < state.samples; i$var21 += 1)
			state.logProbability$bernoulli[i$var21] = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample23) {
			for(int i$var21 = 0; i$var21 < state.samples; i$var21 += 1)
				state.logProbability$sample23[i$var21] = Double.NaN;
		}
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample6)
			logProbabilityValue$sample6();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample23();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample23();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i$var36 = ((state.samples * 2) - ((((state.samples * 2) - 1) % 2) + 1)); i$var36 >= 0; i$var36 -= 2)
			state.flips[(i$var36 / 2)] = state.flipsMeasured[(i$var36 / 2)];
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
		     + "\n"
		     + "public model Flip1CoinMK3(boolean[] flipsMeasured) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "    double bias = beta(1.0, 1.0).sample();\n"
		     + "    boolean[] flips = new boolean[samples];\n"
		     + "        \n"
		     + "    for(int i=0;i<=samples-1;++i) {\n"
		     + "        Bernoulli bernoulli = bernoulli(bias);\n"
		     + "        flips[i] = bernoulli.sample();\n"
		     + "    }\n"
		     + "\n"
		     + "    for(int i=0;i<2*samples;i=i+2) {\n"
		     + "        flips[i/2].observe(flipsMeasured[i/2]);\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}