package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK9$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK9.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK9$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK9$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample7() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample7() {
		state.constrainedFlag$sample7 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var19 = 0; var19 < state.samples; var19 += 1) {
			state.constrainedFlag$sample7 = true;
			cv$count = (cv$count + 1);
			if(state.flips[var19])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample7)
			state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample20() {
		if(!state.fixedProbFlag$sample20) {
			double cv$sampleAccumulator = 0.0;
			for(int var19 = 0; var19 < state.samples; var19 += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips[var19]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var20 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample20 = state.fixedFlag$sample7;
		} else {
			state.logProbability$bernoulli = state.logProbability$var20;
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var20);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var20);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var20);
		}
	}

	private final void logProbabilityValue$sample7() {
		if(!state.fixedProbFlag$sample7) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias, 1.0, 1.0);
			state.logProbability$bias = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample7 = state.fixedFlag$sample7;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			if(state.fixedFlag$sample7)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample7)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int var19 = 0; var19 < state.samples; var19 += 1)
			state.flips[var19] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample7)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int var19 = 0; var19 < state.samples; var19 += 1)
			state.flips[var19] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample7)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample7)
			inferSample7();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample7)
			drawValueSample7();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample7)
			state.logProbability$bias = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample20)
			state.logProbability$var20 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample7)
			logProbabilityValue$sample7();
		logProbabilityValue$sample20();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample20();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample20();
	}

	@Override
	public final void propagateObservedValues() {
		for(int i = (state.samples - 1); i >= 0; i -= 1)
			state.flips[i] = state.flipsMeasured[i];
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
		     + "public model Flip1CoinMK9(boolean[] flipsMeasured) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    double bias = beta(1.0, 1).sample();\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "\n"
		     + "    for(int i:[0..samples))\n"
		     + "        (true == flips[i]).observe(flipsMeasured[i]);\n"
		     + "}\n"
		     + "";
	}
}