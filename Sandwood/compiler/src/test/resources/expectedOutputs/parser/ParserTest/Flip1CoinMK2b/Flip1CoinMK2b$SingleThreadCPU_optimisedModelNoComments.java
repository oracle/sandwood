package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK2b$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK2b.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK2b$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK2b$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample5() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample5() {
		state.constrainedFlag$sample5 = false;
		int cv$sum = 0;
		int cv$count = 0;
		for(int i = 0; i < state.samples; i += 1) {
			state.constrainedFlag$sample5 = true;
			cv$count = (cv$count + 1);
			if(state.flips[i])
				cv$sum = (cv$sum + 1);
		}
		if(state.constrainedFlag$sample5)
			state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, cv$count);
	}

	private final void logProbabilityValue$sample19() {
		if(!state.fixedProbFlag$sample19) {
			double cv$sampleAccumulator = 0.0;
			for(int i = 0; i < state.samples; i += 1)
				cv$sampleAccumulator = (cv$sampleAccumulator + (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flips[i]?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY));
			state.logProbability$bernoulli = cv$sampleAccumulator;
			state.logProbability$var19 = cv$sampleAccumulator;
			state.logProbability$flips = (state.logProbability$flips + cv$sampleAccumulator);
			state.logProbability$$model = (state.logProbability$$model + cv$sampleAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$sampleAccumulator);
			state.fixedProbFlag$sample19 = state.fixedFlag$sample5;
		} else {
			state.logProbability$bernoulli = state.logProbability$var19;
			state.logProbability$flips = (state.logProbability$flips + state.logProbability$var19);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$var19);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$var19);
		}
	}

	private final void logProbabilityValue$sample5() {
		if(!state.fixedProbFlag$sample5) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias, 1.0, 1.0);
			state.logProbability$bias = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample5 = state.fixedFlag$sample5;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			if(state.fixedFlag$sample5)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample5)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i = 0; i < state.samples; i += 1)
			state.flips[i] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample5)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		for(int i = 0; i < state.samples; i += 1)
			state.flips[i] = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample5)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample5)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample5)
			inferSample5();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample5)
			drawValueSample5();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample5)
			state.logProbability$bias = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$flips = 0.0;
		if(!state.fixedProbFlag$sample19)
			state.logProbability$var19 = Double.NaN;
	}

	@Override
	public final void initializeModel() {
		state.samples = state.length$flipsMeasured;
	}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample5)
			logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample19();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
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
		     + "public model Flip1CoinMK2b(boolean[] flipsMeasured) {\n"
		     + "    double a = 1.0;\n"
		     + "    double b = 1.0;\n"
		     + "    double bias = beta(a, b).sample();\n"
		     + "        \n"
		     + "    int samples = flipsMeasured.length;\n"
		     + "        \n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean[] flips = new boolean[samples];\n"
		     + "    for(int i=0;i<samples;i++)\n"
		     + "        flips[i] = bernoulli.sample();\n"
		     + "    flips.observe(flipsMeasured);\n"
		     + "}";
	}
}