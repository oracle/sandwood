package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK1c$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK1c.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK1c$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK1c$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample6() {
		state.var6 = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
	}

	private final void inferSample6() {
		state.constrainedFlag$sample6 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int var18 = 0; var18 < state.samples; var18 += 1) {
			state.constrainedFlag$sample6 = true;
			cv$count = (cv$count + 1);
			if(state.flips[var18])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample6)
			state.var6 = Conjugates.sampleConjugateBetaBinomial(state.RNG$, state.a, state.b, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample19() {
		double cv$sampleAccumulator = 0.0;
		for(int var18 = 0; var18 < state.samples; var18 += 1)
			cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.var6) && (state.var6 <= 1.0))?Math.log((state.flips[var18]?state.var6:(1.0 - state.var6))):Double.NEGATIVE_INFINITY));
		state.logProbability$bernoulli = cv$sampleAccumulator;
		state.logProbability$var19 = cv$sampleAccumulator;
		state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
		state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
		state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
	}

	private final void logProbabilityValue$sample6() {
		double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.var6, state.a, state.b);
		state.logProbability$var6 = cv$distributionAccumulator;
		state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
	}

	@Override
	public final void forwardGeneration() {
		state.var6 = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
		for(int var18 = 0; var18 < state.samples; var18 += 1)
			state.flips[var18] = DistributionSampling.sampleBernoulli(state.RNG$, state.var6);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		state.var6 = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
	}

	@Override
	public final void forwardGenerationPrime() {
		state.var6 = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
		for(int var18 = 0; var18 < state.samples; var18 += 1)
			state.flips[var18] = DistributionSampling.sampleBernoulli(state.RNG$, state.var6);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		state.var6 = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		state.var6 = DistributionSampling.sampleBeta(state.RNG$, state.a, state.b);
	}

	@Override
	public final void gibbsRound() {
		inferSample6();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample6)
			drawValueSample6();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$var6 = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		state.logProbability$var19 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample19();
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
		     + "public model Flip1CoinMK1c(boolean[] flipsMeasured, double a, double b) {\n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(beta(a, b).sample());\n"
		     + "    boolean[] flips = bernoulli.sample(samples);\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}\n"
		     + "";
	}
}