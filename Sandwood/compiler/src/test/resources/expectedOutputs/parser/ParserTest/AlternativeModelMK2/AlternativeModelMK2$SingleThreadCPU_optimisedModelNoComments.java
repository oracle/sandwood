package org.sandwood.compiler.tests.parser;

import org.sandwood.compiler.tests.parser.AlternativeModelMK2$SingleThreadCPU.Scratch;
import org.sandwood.compiler.tests.parser.AlternativeModelMK2.State;
import org.sandwood.runtime.internal.model.CoreModelSingleThreadCPU;
import org.sandwood.runtime.internal.model.state.CoreModelScratch;
import org.sandwood.runtime.internal.numericTools.Conjugates;
import org.sandwood.runtime.internal.numericTools.DistributionSampling;
import org.sandwood.runtime.model.ExecutionTarget;

final class AlternativeModelMK2$SingleThreadCPU extends CoreModelSingleThreadCPU<State, Scratch> {
	final class Scratch implements CoreModelScratch {

		@Override
		public final void allocateScratch() {}
	}


	public AlternativeModelMK2$SingleThreadCPU(State state, ExecutionTarget target) {
		super(state, target);
		scratch = new Scratch();
	}

	private final void drawValueSample6() {
		state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
	}

	private final void inferSample6() {
		state.constrainedFlag$sample6 = false;
		state.constrainedFlag$sample6 = true;
		state.bias = Conjugates.sampleConjugateBetaBinomial(state.RNG$, 1.0, 1.0, state.positiveCount, state.observedSampleCount);
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

	private final void logProbabilityValue$sample8() {
		if(!state.fixedProbFlag$sample8) {
			double cv$distributionAccumulator = DistributionSampling.logProbabilityBinomial(state.positiveCount, state.bias, state.observedSampleCount);
			state.logProbability$binomial = cv$distributionAccumulator;
			state.logProbability$positiveCount = cv$distributionAccumulator;
			state.logProbability$$model = (state.logProbability$$model + cv$distributionAccumulator);
			state.logProbability$$evidence = (state.logProbability$$evidence + cv$distributionAccumulator);
			state.fixedProbFlag$sample8 = state.fixedFlag$sample6;
		} else {
			state.logProbability$binomial = state.logProbability$positiveCount;
			state.logProbability$$model = (state.logProbability$$model + state.logProbability$positiveCount);
			state.logProbability$$evidence = (state.logProbability$$evidence + state.logProbability$positiveCount);
		}
	}

	@Override
	public final void forwardGeneration() {
		if(!state.fixedFlag$sample6)
			state.bias = DistributionSampling.sampleBeta(state.RNG$, 1.0, 1.0);
		state.positiveCount = DistributionSampling.sampleBinomial(state.RNG$, state.bias, state.observedSampleCount);
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
		state.positiveCount = DistributionSampling.sampleBinomial(state.RNG$, state.bias, state.observedSampleCount);
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
		state.logProbability$binomial = 0.0;
		if(!state.fixedProbFlag$sample8)
			state.logProbability$positiveCount = Double.NaN;
	}

	@Override
	public final void initializeModel() {}

	@Override
	public final void logEvidenceProbabilities() {
		initializeLogProbabilityFields();
		if(state.fixedFlag$sample6)
			logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	@Override
	public final void logModelProbabilitiesDist() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	@Override
	public final void logModelProbabilitiesVal() {
		initializeLogProbabilityFields();
		logProbabilityValue$sample6();
		logProbabilityValue$sample8();
	}

	@Override
	public final void propagateObservedValues() {
		state.positiveCount = state.observedPositiveCount;
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
		     + "/**\n"
		     + " * A model for the fairness work.\n"
		     + " */\n"
		     + "public model AlternativeModelMK2(/** The number of observed samples */int observedSampleCount, int observedPositiveCount)  {\n"
		     + "    /** A bias to see how likely values are to be collected. */    \n"
		     + "    double bias = beta(1.0, 1.0).sample();\n"
		     + "        \n"
		     + "    //Construct a binomial\n"
		     + "    /** A binomial distribution for the tests. */\n"
		     + "    Binomial binomial = binomial(bias, observedSampleCount);\n"
		     + "                \n"
		     + "    //Sample from it\n"
		     + "    int positiveCount = binomial.sample();\n"
		     + "        \n"
		     + "    //Link the sampled values to the observed values\n"
		     + "    positiveCount.observe(observedPositiveCount);\n"
		     + "}";
	}
}