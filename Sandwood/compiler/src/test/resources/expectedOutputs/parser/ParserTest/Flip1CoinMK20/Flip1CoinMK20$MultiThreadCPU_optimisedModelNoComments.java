package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.Flip1CoinMK20$MultiThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.Flip1CoinMK20.State;
import org.sandwood.runtime.internal.model.CoreModelMultiThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class Flip1CoinMK20$MultiThreadCPU extends CoreModelMultiThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public Flip1CoinMK20$MultiThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample8() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample8() {
		state.constrainedFlag$sample8 = false;
		state.constrainedFlag$sample8 = true;
		state.constrainedFlag$sample8 = true;
		state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, (state.count1 + state.count2), 200);
	}

	private final void logProbabilityValue$sample11() {
		if(!state.fixedProbFlag$sample11) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(state.count1, state.bias, 100);
			state.logProbability$binomial = (state.logProbability$binomial + cv$distributionAccumulator);
			state.logProbability$count1 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample11 = state.fixedFlag$sample8;
		} else {
			state.logProbability$binomial = (state.logProbability$binomial + state.logProbability$count1);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$count1);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$count1);
		}
	}

	private final void logProbabilityValue$sample12() {
		if(!state.fixedProbFlag$sample12) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(state.count2, state.bias, 100);
			state.logProbability$binomial = (state.logProbability$binomial + cv$distributionAccumulator);
			state.logProbability$count2 = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample12 = state.fixedFlag$sample8;
		} else {
			state.logProbability$binomial = (state.logProbability$binomial + state.logProbability$count2);
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$count2);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$count2);
		}
	}

	private final void logProbabilityValue$sample8() {
		if(!state.fixedProbFlag$sample8) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBeta(state.bias, 1.0, 1.0);
			state.logProbability$bias = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			if(state.fixedFlag$sample8)
				state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample8 = state.fixedFlag$sample8;
		} else {
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$bias);
			if(state.fixedFlag$sample8)
				state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$bias);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample8)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample11)
			state.count1 = DistributionSampling.sampleBinomial(state.RNG$, state.bias, 100);
		if(!state.fixedFlag$sample12)
			state.count2 = DistributionSampling.sampleBinomial(state.RNG$, state.bias, 100);
		if((!state.fixedFlag$sample11 || !state.fixedFlag$sample12))
			state.total = (state.count1 + state.count2);
	}

	@Override
	public final void forwardGenerationDistributionsNoOutputsPrime() {
		if(!state.fixedFlag$sample8)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationPrime() {
		if(!state.fixedFlag$sample8)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		if(!state.fixedFlag$sample11)
			state.count1 = DistributionSampling.sampleBinomial(state.RNG$, state.bias, 100);
		if(!state.fixedFlag$sample12)
			state.count2 = DistributionSampling.sampleBinomial(state.RNG$, state.bias, 100);
		state.total = (state.count1 + state.count2);
	}

	@Override
	public final void forwardGenerationValuesNoOutputs() {
		if(!state.fixedFlag$sample8)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void forwardGenerationValuesNoOutputsPrime() {
		if(!state.fixedFlag$sample8)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	@Override
	public final void gibbsRound() {
		if(!state.fixedFlag$sample8)
			inferSample8();
		state.system$gibbsForward = !state.system$gibbsForward;
		if(!state.constrainedFlag$sample8)
			drawValueSample8();
	}

	private final void initializeLogProbabilityFields() {
		state.logProbability$$model = 0.0;
		state.logProbability$$evidence = 0.0;
		if(!state.fixedProbFlag$sample8)
			state.logProbability$bias = Double.NaN;
		state.logProbability$binomial = 0.0;
		if(!state.fixedProbFlag$sample11)
			state.logProbability$count1 = Double.NaN;
		if(!state.fixedProbFlag$sample12)
			state.logProbability$count2 = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample8)
			logProbabilityValue$sample8();
		logProbabilityValue$sample11();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample11();
		logProbabilityValue$sample12();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample8();
		logProbabilityValue$sample11();
		logProbabilityValue$sample12();
	}

	@Override
	public final void propagateObservedValues() {
		state.fixedFlag$sample11 = false;
		state.fixedFlag$sample12 = false;
		state.count1 = state.obs1;
		state.count2 = state.obs2;
		state.total = (state.obs1 + state.obs2);
	}

	@Override
	public final void setIntermediates() {}

	@Override
	public String modelCode() {
		return "/*\n"
		     + " * Sandwood\n"
		     + " *\n"
		     + " * Copyright (c) 2019-2025, Oracle and/or its affiliates\n"
		     + " * \n"
		     + " * Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/\n"
		     + " */\n"
		     + "\n"
		     + "package org.sandwood.compiler.tests.parser;\n"
		     + "\n"
		     + "public model Flip1CoinMK20(int obs1, int obs2) {\n"
		     + "    \n"
		     + "    double bias = beta(1,1).sample();\n"
		     + "    Binomial binomial = binomial(bias, 100);\n"
		     + "    int count1 = binomial.sample();\n"
		     + "    int count2 = binomial.sample();\n"
		     + "    int total = count1 + count2;\n"
		     + "    \n"
		     + "    count1.observe(obs1);\n"
		     + "    count2.observe(obs2);\n"
		     + "}\n"
		     + "";
	}
}