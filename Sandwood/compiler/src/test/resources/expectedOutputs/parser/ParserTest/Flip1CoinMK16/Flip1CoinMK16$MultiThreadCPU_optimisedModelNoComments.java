package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK16$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK16.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK16$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK16$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample14() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample14() {
		state.constrainedFlag$sample14 = false;
		int cv$sum = 0;
		state.constrainedFlag$sample14 = true;
		if(state.flip)
			cv$sum = 1;
		state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, cv$sum, 1);
	}

	private final void logProbabilityValue$sample14() {
		double cv$accumulator = 0.0;
		if(Double.isNaN(state.guard)) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias, 1.0, 1.0);
			cv$accumulator = cv$distributionAccumulator;
			state.logProbability$sample14 = cv$distributionAccumulator;
		}
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
	}

	private final void logProbabilityValue$sample16() {
		double cv$accumulator = 0.0;
		if(Double.isNaN(state.guard)) {
			double cv$distributionAccumulator = (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flip?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY);
			cv$accumulator = cv$distributionAccumulator;
			state.logProbability$bernoulli = cv$distributionAccumulator;
			state.logProbability$sample16 = cv$distributionAccumulator;
		}
		state.logProbability$$model = (state.logProbability$$model + cv$accumulator);
		state.logProbability$$evidence = (state.logProbability$$evidence + cv$accumulator);
	}

	@Override
	public final void forwardGeneration() {
		if(Double.isNaN(state.guard)) {
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
		}
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(Double.isNaN(state.guard))
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(Double.isNaN(state.guard)) {
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
			state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
		}
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(Double.isNaN(state.guard))
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(Double.isNaN(state.guard))
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(Double.isNaN(state.guard))
			inferSample14();
		state.system$gibbsForward = !state.system$gibbsForward;
		if((Double.isNaN(state.guard) && !state.constrainedFlag$sample14))
			drawValueSample14();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		state.logProbability$sample14 = Double.NaN;
		state.logProbability$bernoulli = Double.NaN;
		state.logProbability$sample16 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample14();
		logProbabilityValue$sample16();
	}

	@Override
	public final void propagateObservedValues() {
		if(Double.isNaN(state.guard))
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
		     + "public model Flip1CoinMK16(boolean flipMeasured, double guard) {\n"
		     + "    if(isNaN(guard)) {\n"
		     + "        double bias = beta(1.0, 1.0).sample();\n"
		     + "        Bernoulli bernoulli = bernoulli(bias);\n"
		     + "        boolean flip = bernoulli.sample();\n"
		     + "        flip.observe(flipMeasured);\n"
		     + "    }\n"
		     + "}\n"
		     + "";
	}
}