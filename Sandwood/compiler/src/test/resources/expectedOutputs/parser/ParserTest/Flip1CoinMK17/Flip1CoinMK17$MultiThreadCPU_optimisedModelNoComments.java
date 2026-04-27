package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK17$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK17.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK17$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK17$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample7() {
		state.bias = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
	}

	private final void inferSample7() {
		state.constrainedFlag$sample7 = false;
		double cv$originalValue = state.bias;
		double cv$var = ((state.bias * state.bias) * 0.010000000000000002);
		if((cv$var < 0.010000000000000002))
			cv$var = 0.010000000000000002;
		double cv$proposedValue = ((Math.sqrt(cv$var) * DistributionSampling.sampleGaussian(state.RNG$)) + state.bias);
		state.constrainedFlag$sample7 = true;
		double cv$originalProbability = ((((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flip?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY) + (((0.0 <= state.bias) && (state.bias <= 1.0))?(DistributionSampling.logProbabilityGaussian((state.bias - 0.5)) + 0.9599163336956222):Double.NEGATIVE_INFINITY));
		state.bias = cv$proposedValue;
		state.constrainedFlag$sample7 = true;
		double cv$ratio = (((((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?Math.log((state.flip?cv$proposedValue:(1.0 - cv$proposedValue))):Double.NEGATIVE_INFINITY) + (((0.0 <= cv$proposedValue) && (cv$proposedValue <= 1.0))?(DistributionSampling.logProbabilityGaussian((cv$proposedValue - 0.5)) + 0.9599163336956222):Double.NEGATIVE_INFINITY)) - cv$originalProbability);
		if(((cv$ratio <= Math.log(DistributionSampling.sampleUniform(state.RNG$))) || Double.isNaN(cv$ratio)))
			state.bias = cv$originalValue;
	}

	private final void logProbabilityValue$sample7() {
		if(!state.fixedProbFlag$sample7) {
			double cv$distributionAccumulator = (((0.0 <= state.bias) && (state.bias <= 1.0))?(DistributionSampling.logProbabilityGaussian((state.bias - 0.5)) + 0.9599163336956222):Double.NEGATIVE_INFINITY);
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

	private final void logProbabilityValue$sample9() {
		if(!state.fixedProbFlag$sample9) {
			double cv$distributionAccumulator = (((0.0 <= state.bias) && (state.bias <= 1.0))?Math.log((state.flip?state.bias:(1.0 - state.bias))):Double.NEGATIVE_INFINITY);
			state.logProbability$bernoulli = cv$distributionAccumulator;
			state.logProbability$flip = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample9 = state.fixedFlag$sample7;
		} else {
			state.logProbability$bernoulli = state.logProbability$flip;
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$flip);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$flip);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample7)
			state.bias = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
		state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.bias = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample7)
			state.bias = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
		state.flip = DistributionSampling.sampleBernoulli(state.RNG$, state.bias);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample7)
			state.bias = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample7)
			state.bias = (DistributionSampling.sampleTruncatedGaussian(state.RNG$, -0.5, 0.3085375387259869, 0.5, 0.6914624612740131) + 0.5);
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
		state.logProbability$bernoulli = 0.0;
		if(!state.fixedProbFlag$sample9)
			state.logProbability$flip = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample7)
			logProbabilityValue$sample7();
		logProbabilityValue$sample9();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample9();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample7();
		logProbabilityValue$sample9();
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
		     + " * Copyright (c) 2019-2024, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK17(boolean flipMeasured) {\n"
		     + "    double bias = truncatedGaussian(0.5, 1.0, 0.0, 1.0).sample();\n"
		     + "    Bernoulli bernoulli = bernoulli(bias);\n"
		     + "    boolean flip = bernoulli.sample();\n"
		     + "    flip.observe(flipMeasured);\n"
		     + "}\n"
		     + "";
	}
}