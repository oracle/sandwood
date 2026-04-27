package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK0$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK0.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK0$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK0$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample5() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample5() {
		state.constrainedFlag$sample5 = false;
		int cv$sum = 0;
		state.constrainedFlag$sample5 = true;
		if(state.flip)
			cv$sum = 1;
		state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, 1);
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

	private final void logProbabilityValue$sample7() {
		if(!state.fixedProbFlag$sample7) {
			double cv$distributionAccumulator = (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flip?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY);
			state.logProbability$bernoulli = cv$distributionAccumulator;
			state.logProbability$flip = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample7 = state.fixedFlag$sample5;
		} else {
			state.logProbability$bernoulli = state.logProbability$flip;
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flip);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flip);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample5)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
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
		state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
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
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample7)
			state.logProbability$flip = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample5)
			logProbabilityValue$sample5();
		logProbabilityValue$sample7();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample7();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample5();
		logProbabilityValue$sample7();
	}

	@Override
	public final void propagateObservedValues() {
		state.flip = state.flipMeasured;
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
		     + "public model Flip1CoinMK0(boolean flipMeasured) {\n"
		     + "    double bias = beta(1.0, 1.0).sample();\n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean flip = bernoulli.sample();\n"
		     + "    flip.observe(flipMeasured);\n"
		     + "}\n"
		     + "";
	}
}